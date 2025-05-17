package com.sean.vehiclemanagementsystem.services;

import com.sean.vehiclemanagementsystem.dtos.request.vehicle.TransferVehicleDTO;
import com.sean.vehiclemanagementsystem.dtos.response.vehicle.VehicleOwnershipResponseDTO;

import java.util.List;

public interface IOwnershipRecordService {

    void transferVehicleOwnership(TransferVehicleDTO dto);
    List<VehicleOwnershipResponseDTO> getOwnershipHistoryByChassis(String chassisNumber);
    List<VehicleOwnershipResponseDTO> getOwnershipHistoryByPlate(String plateNumber);


}
