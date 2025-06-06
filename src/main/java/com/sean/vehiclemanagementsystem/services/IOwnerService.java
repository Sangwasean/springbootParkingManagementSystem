package com.sean.vehiclemanagementsystem.services;

import com.sean.vehiclemanagementsystem.dtos.request.owner.CreateOwnerDTO;
import com.sean.vehiclemanagementsystem.dtos.request.owner.UpdateOwnerDTO;
import com.sean.vehiclemanagementsystem.dtos.response.owner.OwnerResponseDTO;
import com.sean.vehiclemanagementsystem.models.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IOwnerService {

    OwnerResponseDTO createOwner(CreateOwnerDTO dto);

    Owner getOwnerById(UUID id);
    List<String> getPlateNumbersByOwnerId(UUID ownerId);


    OwnerResponseDTO searchOwner(String keyword);

    Page<OwnerResponseDTO> getAllOwners(Pageable pageable);

    Owner updateOwner(UUID id, UpdateOwnerDTO dto);

    void deleteOwner(UUID id);
}
