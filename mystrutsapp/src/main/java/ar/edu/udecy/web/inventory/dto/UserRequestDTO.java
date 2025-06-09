package ar.edu.udecy.web.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String username;
    private String password;
    private Set<String> roles;
}