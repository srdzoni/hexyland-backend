package me.srki.dev.hexyland.backend.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "blog_post")
public class BlogPostEntity extends BaseEntity {

    private String title;
    private String description;
    private String plaintext;
    private String slug;
    private String createdBy;
    private LocalDateTime createdAt;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedAt;
    private String publishedBy;
    private LocalDateTime publishedAt;
    private Boolean published;
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private BlogCategoryEntity category;

    @ManyToMany
    @JoinTable(
            name = "blog_post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<BlogTagEntity> tags;
}
