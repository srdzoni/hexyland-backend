package me.srki.dev.hexyland.backend.model.repositories;

import me.srki.dev.hexyland.backend.model.entities.AppPropertyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppPropertyRepository extends CrudRepository<AppPropertyEntity, Integer> {
    Optional<AppPropertyEntity> findFirstByPropKey(String key);
}
