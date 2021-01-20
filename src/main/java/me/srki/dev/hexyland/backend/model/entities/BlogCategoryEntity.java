package me.srki.dev.hexyland.backend.model.entities;

import javax.persistence.OneToMany;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "blog_category")
public class BlogCategoryEntity extends BaseEntity {
    private String title;
    private String slug;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<BlogPostEntity> posts;
}
