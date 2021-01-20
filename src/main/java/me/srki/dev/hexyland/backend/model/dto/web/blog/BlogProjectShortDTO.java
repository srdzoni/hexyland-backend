package me.srki.dev.hexyland.backend.model.dto.web.blog;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogProjectShortDTO {
  private String title;
  private String slug;
  private Double percentDone;
  private String description;
  private String githubUrl;
  private List<String> tech;
}
