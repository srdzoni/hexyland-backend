package me.srki.dev.hexyland.backend.model.dto.web.blog;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostDTO {
  private String title;
  private String description;
  private String plaintext;
  private String slug;
  private String author;
  private Long publishedAt;
  private Set<String> tags;
  private String category;
}
