package com.thetestroom.monster_api.models;

public class Monster {
    private String id;
    private String name;
    private String type;

    // No-Args Constructor
    public Monster() {
    }

    // All-Args Constructor
    public Monster(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    // toString Method
    @Override
    public String toString() {
        return "Monster{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
