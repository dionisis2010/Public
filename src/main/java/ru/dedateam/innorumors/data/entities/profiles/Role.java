package ru.dedateam.innorumors.data.entities.profiles;

import lombok.Getter;

@Getter
public enum Role {
    USER("user"),
    ADMIN("admin");

    private String name;

    Role(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
