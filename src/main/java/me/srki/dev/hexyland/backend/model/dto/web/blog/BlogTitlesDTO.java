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
public class BlogTitlesDTO {
  private String title;
  private String slug;
  private LocalDateTime timestamp;
  private String category;
  private List<String> tags;
}
