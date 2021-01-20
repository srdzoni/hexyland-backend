package me.srki.dev.hexyland.backend.model.entities;

import javax.persistence.UniqueConstraint;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "blog_tag")
public class BlogTagEntity extends BaseEntity {
    private String name;
    private String slug;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private Set<BlogPostEntity> posts;
}
