package me.srki.dev.hexyland.backend.model.repositories;

import me.srki.dev.hexyland.backend.model.entities.BlogCategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogCategoryRepository extends CrudRepository<BlogCategoryEntity, Integer> {
}
