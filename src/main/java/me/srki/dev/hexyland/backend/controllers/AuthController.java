package me.srki.dev.hexyland.backend.controllers;

import lombok.extern.slf4j.Slf4j;
import me.srki.dev.hexyland.backend.exceptions.model.*;
import me.srki.dev.hexyland.backend.model.dto.UserDTO;
import me.srki.dev.hexyland.backend.model.dto.auth.LoginRequestDTO;
import me.srki.dev.hexyland.backend.model.dto.auth.LoginResponseDTO;
import me.srki.dev.hexyland.backend.model.dto.auth.RegisterRequestDTO;
import me.srki.dev.hexyland.backend.model.dto.web.MessageResponseDTO;
import me.srki.dev.hexyland.backend.services.auth.AuthService;
import me.srki.dev.hexyland.backend.services.auth.PropertyService;
import me.srki.dev.hexyland.backend.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final AuthService authService;
  private final JwtUtils jwtUtils;
  private final PropertyService propertyService;

  @Autowired
  public AuthController(
      AuthenticationManager authenticationManager,
      AuthService authService,
      JwtUtils jwtUtils,
      PropertyService propertyService) {
    this.authenticationManager = authenticationManager;
    this.authService = authService;
    this.jwtUtils = jwtUtils;
    this.propertyService = propertyService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequest) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    try {
      UserDTO user = authService.loginUser(loginRequest);
      return ResponseEntity.ok(LoginResponseDTO.builder().user(user).jwt(jwt).build());
    } catch (UserNotFoundException e) {
      return ResponseEntity.badRequest().body(new MessageResponseDTO("ERROR", "User not found"));
    } catch (UserNotActiveException e) {
      return ResponseEntity.badRequest().body(new MessageResponseDTO("ERROR", "User not active"));
    }
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDTO registerRequestDTO) {

    try {
      if(!propertyService.isRegistrationEnabled()) {
        return ResponseEntity.badRequest()
            .body(new MessageResponseDTO("ERROR", "Registrations are currently disabled"));
      }
    } catch (AppPropertyNotFoundException e) {
      log.error("Application property not found: {}", e.getLocalizedMessage());
    }

    try {
      return ResponseEntity.ok(authService.registerUser(registerRequestDTO));
    } catch (AuthorityNotFoundException e) {
      return ResponseEntity.badRequest()
          .body(new MessageResponseDTO("ERROR", "User authority not found"));
    } catch (UsernameAlreadyExistsException e) {
      return ResponseEntity.badRequest()
          .body(new MessageResponseDTO("ERROR", "Username already exists"));
    } catch (EmailAlreadyExistsException e) {
      return ResponseEntity.badRequest()
          .body(new MessageResponseDTO("ERROR", "Email already exists"));
    }
  }
}
