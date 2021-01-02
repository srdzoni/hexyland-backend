package me.srki.dev.hexyland.backend.model.entities;

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
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private Set<BlogPostEntity> posts;
}
