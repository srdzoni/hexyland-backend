package me.srki.dev.hexyland.backend.model.dto.web.blog;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostShortDTO {
  private Integer id;
  private String title;
  private String slug;
  private String category;
  private String categorySlug;
  private Long timestamp;
  private String description;
  private List<String> tags;
}
