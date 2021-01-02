package me.srki.dev.hexyland.backend.model.repositories;

import me.srki.dev.hexyland.backend.model.entities.BlogPostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends CrudRepository<BlogPostEntity, Integer> {
}
