package me.srki.dev.hexyland.backend.services.auth.impl;

import me.srki.dev.hexyland.backend.exceptions.model.AppPropertyNotFoundException;
import me.srki.dev.hexyland.backend.model.entities.AppPropertyEntity;
import me.srki.dev.hexyland.backend.model.repositories.AppPropertyRepository;
import me.srki.dev.hexyland.backend.services.auth.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final AppPropertyRepository propertyRepository;

    @Autowired
    public PropertyServiceImpl(AppPropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Boolean isRegistrationEnabled() throws AppPropertyNotFoundException {
        AppPropertyEntity prop = propertyRepository.findFirstByPropKey("registrations_enabled").orElseThrow(AppPropertyNotFoundException::new);
        return Boolean.parseBoolean(prop.getPropValue());
    }
}
