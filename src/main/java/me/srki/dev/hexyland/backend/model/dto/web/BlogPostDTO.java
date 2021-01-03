package me.srki.dev.hexyland.backend.model.dto.web;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostDTO {
  private String title;
  private String description;
  private String plaintext;
  private String slug;
  private String author;
  private LocalDateTime publishedAt;
  private Set<String> tags;
  private Set<String> categories;
}
