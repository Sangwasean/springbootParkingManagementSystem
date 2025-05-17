package com.sean.vehiclemanagementsystem.services;



import com.sean.vehiclemanagementsystem.dtos.request.auth.UpdateUserDTO;
import com.sean.vehiclemanagementsystem.dtos.request.user.CreateAdminDTO;
import com.sean.vehiclemanagementsystem.dtos.request.user.UserResponseDTO;
import com.sean.vehiclemanagementsystem.dtos.request.user.UserRoleModificationDTO;
import com.sean.vehiclemanagementsystem.models.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    User findUserById(UUID userId);

    User getLoggedInUser();

    UserResponseDTO createAdmin(CreateAdminDTO createUserDTO);

    List<User> getUsers();

    UserResponseDTO getUserById(UUID uuid);

    UserResponseDTO updateUser(UUID userId, UpdateUserDTO updateUserDTO);

    UserResponseDTO addRoles(UUID userId, UserRoleModificationDTO userRoleModificationDTO);

    UserResponseDTO removeRoles(UUID userId, UserRoleModificationDTO userRoleModificationDTO);

    void deleteUser(UUID userId);
}
