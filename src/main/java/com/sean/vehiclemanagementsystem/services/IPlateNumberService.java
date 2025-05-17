package com.mikepn.vehiclemanagementsystem.services;

import com.mikepn.vehiclemanagementsystem.models.PlateNumber;

import java.util.List;
import java.util.UUID;
public interface IPlateNumberService {
    PlateNumber assignPlateNumberToOwner(UUID ownerId, String plateNumber);
    List<PlateNumber> getAllPlateNumbers();
}
