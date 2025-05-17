package com.sean.vehiclemanagementsystem.services;

import com.sean.vehiclemanagementsystem.models.PlateNumber;

import java.util.List;
import java.util.UUID;
public interface IPlateNumberService {
    PlateNumber assignPlateNumberToOwner(UUID ownerId, String plateNumber);
    List<PlateNumber> getAllPlateNumbers();
}
