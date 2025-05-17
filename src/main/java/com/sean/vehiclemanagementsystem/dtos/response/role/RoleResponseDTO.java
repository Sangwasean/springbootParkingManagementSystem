package com.sean.vehiclemanagementsystem.dtos.response.role;


import com.sean.vehiclemanagementsystem.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RoleResponseDTO {
    private Role role;
}