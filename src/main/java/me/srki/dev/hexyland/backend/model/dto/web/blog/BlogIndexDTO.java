package me.srki.dev.hexyland.backend.model.dto.web.blog;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogIndexDTO {
  private List<BlogProjectShortDTO> projects;
  private List<BlogPostShortDTO> writings;
  private List<String> misc;
}
