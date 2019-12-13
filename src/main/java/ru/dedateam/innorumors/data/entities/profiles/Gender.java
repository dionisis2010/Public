package ru.dedateam.innorumors.data.entities.profiles;

import lombok.Getter;

public enum Gender {

    MALE("мужской"),
    FEMALE("женский");

    private Boolean gender;
    private String name;


    Gender(String name) {
        this.gender = gender;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
