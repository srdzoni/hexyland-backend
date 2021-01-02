package me.srki.dev.hexyland.backend.util.converters;

import me.srki.dev.hexyland.backend.model.dto.UserAuthorityDTO;
import me.srki.dev.hexyland.backend.model.dto.UserDTO;
import me.srki.dev.hexyland.backend.model.entities.AuthorityEntity;
import me.srki.dev.hexyland.backend.model.entities.UserEntity;

import java.util.stream.Collectors;

public class EntityToDTOConverter {

    public static UserDTO convert(UserEntity entity) {
        return UserDTO.builder()
                .username(entity.getUsername())
                .email(entity.getEmail())
                .fullName(entity.getFullName())
                .authorities(
                        entity.getAuthorities()
                                .stream()
                                .map(authority -> EntityToDTOConverter.convert(authority))
                                .collect(Collectors.toUnmodifiableSet())
                )
                .build();
    }

    public static UserAuthorityDTO convert(AuthorityEntity entity) {
        return new UserAuthorityDTO(entity.getName());
    }
}
