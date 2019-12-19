package ru.dedateam.innorumors.data.entities.profiles;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_BANED("ROLE_BANED");

    private String name;

    Role(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
