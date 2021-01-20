package me.srki.dev.hexyland.backend.controllers;

import me.srki.dev.hexyland.backend.exceptions.model.BlogPostNotFoundException;
import me.srki.dev.hexyland.backend.model.dto.web.MessageResponseDTO;
import me.srki.dev.hexyland.backend.services.web.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/blog")
public class BlogIndexController {

  private final BlogService blogService;

  @Autowired
  public BlogIndexController(BlogService blogService) {
    this.blogService = blogService;
  }

  @GetMapping("/index")
  public ResponseEntity index() {
    return ResponseEntity.ok(blogService.getIndexData());
  }

  @GetMapping("/post/{slug}")
  public ResponseEntity postBySlug(@PathVariable String slug) {
    try {
      return ResponseEntity.ok(blogService.getPostBySlug(slug));
    } catch (BlogPostNotFoundException e) {
      return ResponseEntity.badRequest().body(new MessageResponseDTO("ERROR", "Blog post not found"));
    }
  }

  @GetMapping("/posts")
  public ResponseEntity all() {
    return ResponseEntity.ok(blogService.getAllShortWithCategoriesAndTags());
  }
}
