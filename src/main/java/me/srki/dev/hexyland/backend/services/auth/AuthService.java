package me.srki.dev.hexyland.backend.services.auth;

import me.srki.dev.hexyland.backend.exceptions.model.*;
import me.srki.dev.hexyland.backend.model.dto.UserDTO;
import me.srki.dev.hexyland.backend.model.dto.auth.LoginRequestDTO;
import me.srki.dev.hexyland.backend.model.dto.auth.RegisterRequestDTO;

public interface AuthService {
    UserDTO registerUser(RegisterRequestDTO request) throws AuthorityNotFoundException, UsernameAlreadyExistsException, EmailAlreadyExistsException;
    UserDTO loginUser(LoginRequestDTO requestDTO) throws UserNotFoundException, UserNotActiveException;
}
