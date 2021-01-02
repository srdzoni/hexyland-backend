package me.srki.dev.hexyland.backend.model.repositories;

import me.srki.dev.hexyland.backend.model.entities.AuthorityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends CrudRepository<AuthorityEntity, Integer> {
    Optional<AuthorityEntity> findByName(String name);
}
