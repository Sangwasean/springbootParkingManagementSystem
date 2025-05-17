package com.sean.vehiclemanagementsystem.services.impl;

import com.sean.vehiclemanagementsystem.enums.EPlateStatus;
import com.sean.vehiclemanagementsystem.exceptions.NotFoundException;
import com.sean.vehiclemanagementsystem.models.Owner;
import com.sean.vehiclemanagementsystem.models.PlateNumber;
import com.sean.vehiclemanagementsystem.repositories.IOwnerRepository;
import com.sean.vehiclemanagementsystem.repositories.IPlateNumberRepository;
import com.sean.vehiclemanagementsystem.services.IPlateNumberService;
import com.sean.vehiclemanagementsystem.utils.helpers.PlateNumberValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlateNumberServiceImpl implements IPlateNumberService {

    private final IPlateNumberRepository plateNumberRepository;
    private final IOwnerRepository ownerRepository;
    private final PlateNumberValidator plateNumberValidator;

    @Override
    @Transactional
    public PlateNumber assignPlateNumberToOwner(UUID ownerId, String plateNumber) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new NotFoundException("Owner not found with ID: " + ownerId));

        // Validate the user-provided plate number
        plateNumberValidator.validatePlateNumber(plateNumber);

        PlateNumber plate = PlateNumber.builder()
                .owner(owner)
                .plateNumber(plateNumber)
                .plateStatus(EPlateStatus.AVAILABLE)
                .issuedDate(LocalDateTime.now())
                .build();

        return plateNumberRepository.save(plate);
    }

    @Override
    public List<PlateNumber> getAllPlateNumbers() {
        return plateNumberRepository.findAll();
    }
}
