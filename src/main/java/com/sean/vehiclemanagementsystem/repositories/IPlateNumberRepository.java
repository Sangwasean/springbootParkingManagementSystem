package com.sean.vehiclemanagementsystem.repositories;

import com.sean.vehiclemanagementsystem.models.PlateNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IPlateNumberRepository extends JpaRepository<PlateNumber, UUID> {
    Optional<PlateNumber> findTopByOrderByIssuedDateDesc();
    boolean existsByPlateNumber(String plateNumber);

}
