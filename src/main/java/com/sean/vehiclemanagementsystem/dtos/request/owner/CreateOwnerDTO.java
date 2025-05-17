package com.sean.vehiclemanagementsystem.dtos.request.owner;


import com.sean.vehiclemanagementsystem.dtos.request.auth.RegisterUserDTO;
import lombok.Data;

@Data
public class CreateOwnerDTO extends RegisterUserDTO {
    private String address;
}
