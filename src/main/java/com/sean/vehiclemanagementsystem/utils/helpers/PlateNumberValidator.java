package com.mikepn.vehiclemanagementsystem.utils.helpers;

import com.mikepn.vehiclemanagementsystem.exceptions.AppException;
import com.mikepn.vehiclemanagementsystem.repositories.IPlateNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class PlateNumberValidator {

    private static final String PREFIX_START = "RAA";
    private static final String PREFIX_END = "RZZ";
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 999;
    private static final char MIN_SUFFIX = 'A';
    private static final char MAX_SUFFIX = 'Z';
    private static final String PLATE_NUMBER_PATTERN = "^R[A-Z]{2}\\s[0-9]{3}\\s[A-Z]$";

    private final IPlateNumberRepository plateNumberRepository;

    public void validatePlateNumber(String plateNumber) {
        // Check for null or empty input
        if (plateNumber == null || plateNumber.trim().isEmpty()) {
            throw new AppException("Plate number cannot be empty");
        }

        // Normalize input (trim and ensure single spaces)
        plateNumber = plateNumber.trim().replaceAll("\\s+", " ");

        // Check format with regex
        if (!Pattern.matches(PLATE_NUMBER_PATTERN, plateNumber)) {
            throw new AppException("Plate number must match format 'RXX NNN X' (e.g., 'RAA 001 A')");
        }

        // Split and validate components
        String[] parts = plateNumber.split(" ");
        if (parts.length != 3) {
            throw new AppException("Invalid plate number format. Expected 'RXX NNN X'");
        }

        String prefix = parts[0];
        String numberStr = parts[1];
        char suffix = parts[2].charAt(0);

        // Validate prefix (RAA to RZZ)
        if (prefix.compareTo(PREFIX_START) < 0 || prefix.compareTo(PREFIX_END) > 0) {
            throw new AppException("Prefix must be between 'RAA' and 'RZZ'");
        }

        // Validate number (001 to 999)
        int number;
        try {
            number = Integer.parseInt(numberStr);
        } catch (NumberFormatException e) {
            throw new AppException("Number part must be a valid integer");
        }
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new AppException("Number must be between 001 and 999");
        }

        // Validate suffix (A to Z)
        if (suffix < MIN_SUFFIX || suffix > MAX_SUFFIX) {
            throw new AppException("Suffix must be a letter between 'A' and 'Z'");
        }

        // Check for uniqueness
        if (plateNumberRepository.existsByPlateNumber(plateNumber)) {
            throw new AppException("Plate number '" + plateNumber + "' is already allocated");
        }
    }
}