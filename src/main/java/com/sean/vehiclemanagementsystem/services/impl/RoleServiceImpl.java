package com.sean.vehiclemanagementsystem.services.impl;


import com.sean.vehiclemanagementsystem.dtos.request.role.CreateRoleDTO;
import com.sean.vehiclemanagementsystem.dtos.response.role.RoleResponseDTO;
import com.sean.vehiclemanagementsystem.dtos.response.role.RolesResponseDTO;
import com.sean.vehiclemanagementsystem.enums.ERole;
import com.sean.vehiclemanagementsystem.exceptions.InternalServerErrorException;
import com.sean.vehiclemanagementsystem.exceptions.NotFoundException;
import com.sean.vehiclemanagementsystem.models.Role;
import com.sean.vehiclemanagementsystem.repositories.IRoleRepository;
import com.sean.vehiclemanagementsystem.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository roleRepository;

    @Override
    public Role getRoleById(UUID roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("Role with ID " + roleId + " not found"));
    }

    @Override
    public Role getRoleByName(ERole roleName) {
        return roleRepository.findRoleByName(roleName)
                .orElseThrow(() -> new NotFoundException("Role " + roleName + " not found"));
    }

    @Override
    public void createRole(ERole roleName) {
        if (roleRepository.findRoleByName(roleName).isPresent()) {
            throw new InternalServerErrorException("Role " + roleName + " already exists");
        }

        Role role = Role.builder()
                .name(roleName)
                .build();

        try {
            roleRepository.save(role);
        } catch (Exception e) {
            throw new InternalServerErrorException("Could not create role: " + e.getMessage());
        }
    }

    @Override
    public RoleResponseDTO createRole(CreateRoleDTO createRoleDTO) {
        ERole roleName = ERole.valueOf(createRoleDTO.getName().name().toUpperCase());

        if (roleRepository.findRoleByName(roleName).isPresent()) {
            throw new InternalServerErrorException("Role " + roleName + " already exists");
        }

        Role role = Role.builder()
                .name(roleName)
                .build();

        try {
            Role savedRole = roleRepository.save(role);
            return RoleResponseDTO.builder()
                     .role(savedRole)
                    .build();
        } catch (Exception e) {
            throw new InternalServerErrorException("Could not create role: " + e.getMessage());
        }
    }

    @Override
    public RolesResponseDTO getRoles(Pageable pageable) {
        try {
            Page<Role> rolePage = roleRepository.findAll(pageable);
            return RolesResponseDTO.builder()
                    .roles(rolePage)
                    .build();
        } catch (Exception e) {
            throw new InternalServerErrorException("Failed to fetch roles: " + e.getMessage());
        }
    }



    @Override
    public void deleteRole(UUID roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("The role is not present"));

        try {
            roleRepository.delete(role);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public boolean isRolePresent(ERole roleName) {
        try {
            return roleRepository.findRoleByName(roleName).isPresent();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
