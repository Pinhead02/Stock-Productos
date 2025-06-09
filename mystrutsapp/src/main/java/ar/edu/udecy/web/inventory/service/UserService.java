package ar.edu.udecy.web.inventory.service;

import ar.edu.udecy.web.inventory.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO isValidUser(String username, String password);
}
