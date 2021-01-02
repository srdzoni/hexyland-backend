package me.srki.dev.hexyland.backend.services.auth;

import me.srki.dev.hexyland.backend.exceptions.model.AppPropertyNotFoundException;

public interface PropertyService {
    Boolean isRegistrationEnabled() throws AppPropertyNotFoundException;
}
