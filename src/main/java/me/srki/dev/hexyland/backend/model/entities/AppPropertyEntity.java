package me.srki.dev.hexyland.backend.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "app_property")
public class AppPropertyEntity extends BaseEntity {
    private String propKey;
    private String propValue;
}
