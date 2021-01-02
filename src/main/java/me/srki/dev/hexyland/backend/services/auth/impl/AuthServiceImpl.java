package me.srki.dev.hexyland.backend.services.auth.impl;

import me.srki.dev.hexyland.backend.exceptions.model.*;
import me.srki.dev.hexyland.backend.model.dto.UserDTO;
import me.srki.dev.hexyland.backend.model.dto.auth.LoginRequestDTO;
import me.srki.dev.hexyland.backend.model.dto.auth.RegisterRequestDTO;
import me.srki.dev.hexyland.backend.model.entities.AuthorityEntity;
import me.srki.dev.hexyland.backend.model.entities.UserEntity;
import me.srki.dev.hexyland.backend.model.repositories.AuthorityRepository;
import me.srki.dev.hexyland.backend.model.repositories.UserRepository;
import me.srki.dev.hexyland.backend.services.auth.AuthService;
import me.srki.dev.hexyland.backend.util.converters.EntityToDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private PasswordEncoder encoder;


    @Autowired
    public AuthServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDTO registerUser(RegisterRequestDTO request) throws AuthorityNotFoundException, UsernameAlreadyExistsException, EmailAlreadyExistsException {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }
        UserEntity user = new UserEntity();
        AuthorityEntity authority = authorityRepository.findByName("ROLE_USER").orElseThrow(AuthorityNotFoundException::new);
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setActive(true);
        user.setDeleted(false);
        user.setAuthorities(Collections.singleton(authority));
        user.setRegistrationDate(LocalDateTime.now());
        return EntityToDTOConverter.convert(userRepository.save(user));
    }

    @Override
    public UserDTO loginUser(LoginRequestDTO requestDTO) throws UserNotFoundException, UserNotActiveException {
        UserEntity entity = userRepository.findByUsername(requestDTO.getUsername()).orElseThrow(UserNotFoundException::new);
        if(!entity.getActive()) throw new UserNotActiveException();

        return EntityToDTOConverter.convert(entity);
    }
}
