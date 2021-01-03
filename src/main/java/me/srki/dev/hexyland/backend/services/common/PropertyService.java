package me.srki.dev.hexyland.backend.services.common;

import me.srki.dev.hexyland.backend.exceptions.model.AppPropertyNotFoundException;

public interface PropertyService {
    Boolean isRegistrationEnabled() throws AppPropertyNotFoundException;
}
