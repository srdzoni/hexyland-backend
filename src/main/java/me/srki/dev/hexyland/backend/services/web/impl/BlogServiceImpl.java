package me.srki.dev.hexyland.backend.services.web.impl;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import me.srki.dev.hexyland.backend.exceptions.model.BlogPostNotFoundException;
import me.srki.dev.hexyland.backend.model.dto.web.blog.BlogCategoryDTO;
import me.srki.dev.hexyland.backend.model.dto.web.blog.BlogIndexDTO;
import me.srki.dev.hexyland.backend.model.dto.web.blog.BlogPostDTO;
import me.srki.dev.hexyland.backend.model.dto.web.blog.BlogPostShortDTO;
import me.srki.dev.hexyland.backend.model.dto.web.blog.BlogTagDTO;
import me.srki.dev.hexyland.backend.model.entities.BlogPostEntity;
import me.srki.dev.hexyland.backend.model.entities.BlogTagEntity;
import me.srki.dev.hexyland.backend.model.repositories.BlogCategoryRepository;
import me.srki.dev.hexyland.backend.model.repositories.BlogPostRepository;
import me.srki.dev.hexyland.backend.model.repositories.BlogTagRepository;
import me.srki.dev.hexyland.backend.services.web.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

  private final BlogPostRepository blogPostRepository;
  private final BlogTagRepository blogTagRepository;
  private final BlogCategoryRepository blogCategoryRepository;

  @Autowired
  public BlogServiceImpl(BlogPostRepository blogPostRepository, BlogTagRepository blogTagRepository, BlogCategoryRepository blogCategoryRepository) {
    this.blogPostRepository = blogPostRepository;
    this.blogTagRepository = blogTagRepository;
    this.blogCategoryRepository = blogCategoryRepository;
  }

  @Override
  public BlogIndexDTO getIndexData() {
    BlogIndexDTO indexDTO = new BlogIndexDTO();
    indexDTO.setWritings(new ArrayList<>());
    indexDTO.setMisc(new ArrayList<>());
    indexDTO.setProjects(new ArrayList<>());
    blogPostRepository
        .findAll()
        .forEach(
            blogPostEntity ->
                indexDTO
                    .getWritings()
                    .add(
                        BlogPostShortDTO.builder()
                            .id(blogPostEntity.getId())
                            .title(blogPostEntity.getTitle())
                            .description(blogPostEntity.getDescription())
                            .category(blogPostEntity.getCategory().getTitle())
                            .categorySlug(blogPostEntity.getCategory().getSlug())
                            .slug(blogPostEntity.getSlug())
                            .tags(blogPostEntity.getTags().stream().map(blogTagEntity -> blogTagEntity.getName()).collect(Collectors.toList()))
                            .timestamp(
                                blogPostEntity
                                    .getCreatedAt()
                                    .toEpochSecond(ZoneOffset.of("+02:00")) * 1000)
                            .build()));
    return indexDTO;
  }

  @Override
  public List<BlogPostShortDTO> getAllByCategory(String category) {
    return null;
  }

  @Override
  public List<BlogPostShortDTO> getAllByTag(String tag) {
    return null;
  }



  @Override
  public List<BlogPostShortDTO> getAllOrderByDate() {
    List<BlogPostEntity> entities = blogPostRepository.findAllByOrderByPublishedAtDesc();
    List<BlogPostShortDTO> posts = new ArrayList<>();
    entities.forEach(blogPostEntity -> {
      posts.add(BlogPostShortDTO.builder()
          .id(blogPostEntity.getId())
          .title(blogPostEntity.getTitle())
          .description(blogPostEntity.getDescription())
          .category(blogPostEntity.getCategory().getTitle())
          .categorySlug(blogPostEntity.getCategory().getSlug())
          .slug(blogPostEntity.getSlug())
          .tags(blogPostEntity.getTags().stream().map(blogTagEntity -> blogTagEntity.getName()).collect(Collectors.toList()))
          .timestamp(
              blogPostEntity
                  .getCreatedAt()
                  .toEpochSecond(ZoneOffset.of("+02:00")) * 1000)
          .build());
    });
    return posts;
  }

  @Override
  public List<BlogPostShortDTO> getPage(Integer pageNumber, Integer pageSize) {
    return null;
  }

  @Override
  public BlogPostDTO getPostBySlug(String slug) throws BlogPostNotFoundException {
    BlogPostEntity entity = blogPostRepository.getFirstBySlug(slug).orElseThrow(
        BlogPostNotFoundException::new);

    return BlogPostDTO.builder()
        .title(entity.getTitle())
        .author(entity.getAuthor().getFullName())
        .slug(entity.getSlug())
        .publishedAt(entity.getPublishedAt().toEpochSecond(ZoneOffset.of("+02:00")) * 1000)
        .description(entity.getDescription())
        .plaintext(entity.getPlaintext())
        .category(entity.getCategory().getTitle())
        .tags(entity.getTags().stream().map(blogTagEntity -> blogTagEntity.getName()).collect(Collectors.toSet()))
        .build();
  }

  @Override
  public Map<String, Object> getAllShortWithCategoriesAndTags() {
    List<BlogPostEntity> entities = blogPostRepository.findAllByOrderByPublishedAtDesc();

    List<BlogTagDTO> tags = new ArrayList<>();
    List<BlogCategoryDTO> categories = new ArrayList<>();
    blogTagRepository.findAll().forEach(blogTagEntity -> tags.add(new BlogTagDTO(blogTagEntity.getName(), blogTagEntity.getSlug())));
    blogCategoryRepository.findAll().forEach(blogCategoryEntity -> categories.add(new BlogCategoryDTO(blogCategoryEntity.getTitle(), blogCategoryEntity.getSlug())));

    List<BlogPostShortDTO> posts = new ArrayList<>();
    entities.forEach(blogPostEntity -> {
      posts.add(BlogPostShortDTO.builder()
          .id(blogPostEntity.getId())
          .title(blogPostEntity.getTitle())
          .description(blogPostEntity.getDescription())
          .category(blogPostEntity.getCategory().getTitle())
          .categorySlug(blogPostEntity.getCategory().getSlug())
          .slug(blogPostEntity.getSlug())
          .tags(blogPostEntity.getTags().stream().map(blogTagEntity -> blogTagEntity.getName()).collect(Collectors.toList()))
          .timestamp(
              blogPostEntity
                  .getCreatedAt()
                  .toEpochSecond(ZoneOffset.of("+02:00")) * 1000)
          .build());
    });

    Map<String, Object> map = new HashMap<>();
    map.put("tags", tags);
    map.put("categories", categories);
    map.put("posts", posts);
    return map;
  }
}
