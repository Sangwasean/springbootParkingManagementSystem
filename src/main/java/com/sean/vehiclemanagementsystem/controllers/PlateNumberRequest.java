package com.sean.vehiclemanagementsystem.controllers;

import java.util.UUID;

public class PlateNumberRequest {
    private UUID ownerId;
    private String plateNumber;

    public UUID getOwnerId() { return ownerId; }
    public void setOwnerId(UUID ownerId) { this.ownerId = ownerId; }
    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
}