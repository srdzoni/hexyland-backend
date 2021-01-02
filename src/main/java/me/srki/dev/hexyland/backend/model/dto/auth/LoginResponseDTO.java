package me.srki.dev.hexyland.backend.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.srki.dev.hexyland.backend.model.dto.UserDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {
    private String jwt;
    private UserDTO user;
}
