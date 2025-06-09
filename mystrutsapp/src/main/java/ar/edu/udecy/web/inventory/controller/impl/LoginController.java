package ar.edu.udecy.web.inventory.controller.impl;

import ar.edu.udecy.web.inventory.config.JwtUtil;
import ar.edu.udecy.web.inventory.dto.UserRequestDTO;
import ar.edu.udecy.web.inventory.dto.UserResponseDTO;
import ar.edu.udecy.web.inventory.dto.UserValidateResponseDTO;
import ar.edu.udecy.web.inventory.service.UserValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserValidateService userValidateService;

    @PostMapping("login")
    public UserValidateResponseDTO login(@RequestBody UserRequestDTO userRequestDTO) {

        return userValidateService.isValidUser(userRequestDTO.getUsername(), userRequestDTO.getPassword());
    }
}