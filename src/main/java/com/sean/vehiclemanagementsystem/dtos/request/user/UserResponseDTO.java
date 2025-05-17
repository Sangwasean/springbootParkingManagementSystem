package com.sean.vehiclemanagementsystem.dtos.request.user;


import com.sean.vehiclemanagementsystem.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private User user;
}