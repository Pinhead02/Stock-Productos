package ar.edu.udecy.web.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserValidateResponseDTO {
    private String username;
    private Set<String> roles;
    private String token;
}
