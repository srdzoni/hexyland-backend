package me.srki.dev.hexyland.backend.services.web;


import java.util.List;
import java.util.Map;
import me.srki.dev.hexyland.backend.exceptions.model.BlogPostNotFoundException;
import me.srki.dev.hexyland.backend.model.dto.web.blog.BlogIndexDTO;
import me.srki.dev.hexyland.backend.model.dto.web.blog.BlogPostDTO;
import me.srki.dev.hexyland.backend.model.dto.web.blog.BlogPostShortDTO;

public interface BlogService {
  BlogIndexDTO getIndexData();
  List<BlogPostShortDTO> getAllByCategory(String category);
  List<BlogPostShortDTO> getAllByTag(String tag);
  List<BlogPostShortDTO> getAllOrderByDate();
  List<BlogPostShortDTO> getPage(Integer pageNumber, Integer pageSize);
  BlogPostDTO getPostBySlug(String slug) throws BlogPostNotFoundException;
  Map<String, Object> getAllShortWithCategoriesAndTags();
}
