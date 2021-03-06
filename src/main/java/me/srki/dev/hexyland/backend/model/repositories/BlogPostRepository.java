package me.srki.dev.hexyland.backend.model.repositories;

import java.util.List;
import java.util.Optional;
import me.srki.dev.hexyland.backend.model.entities.BlogPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPostEntity, Integer> {

  List<BlogPostEntity> findAllByOrderByPublishedAtDesc();
  Optional<BlogPostEntity> getFirstBySlug(String slug);
}
