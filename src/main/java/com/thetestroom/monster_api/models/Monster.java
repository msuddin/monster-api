package com.thetestroom.monster_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Monster {

    @Id
    private Long id;
    private String name;
    private String type;

    // Default constructor
    public Monster() {
    }

    // Constructor with parameters
    public Monster(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Override toString(), equals(), and hashCode() if needed for better clarity
    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
