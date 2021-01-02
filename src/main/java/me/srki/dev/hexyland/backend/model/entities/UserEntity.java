package me.srki.dev.hexyland.backend.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"posts", "authorities"})
@Entity
@Table(name = "hexuser",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class UserEntity extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private String fullName;
    private LocalDateTime registrationDate;
    private Boolean active;
    private Boolean deleted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private Set<BlogPostEntity> posts;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<AuthorityEntity> authorities;
}
