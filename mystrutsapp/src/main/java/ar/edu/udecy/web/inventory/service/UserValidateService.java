package ar.edu.udecy.web.inventory.service;

import ar.edu.udecy.web.inventory.dto.UserResponseDTO;
import ar.edu.udecy.web.inventory.dto.UserValidateResponseDTO;

public interface UserValidateService {
    UserValidateResponseDTO isValidUser(String username, String password);
}
