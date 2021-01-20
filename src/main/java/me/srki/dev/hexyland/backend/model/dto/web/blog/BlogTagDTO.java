package me.srki.dev.hexyland.backend.model.dto.web.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogTagDTO {
  private String title;
  private String slug;
}
