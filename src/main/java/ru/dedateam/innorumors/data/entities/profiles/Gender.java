package ru.dedateam.innorumors.data.entities.profiles;

import lombok.Getter;

public enum Gender {

    NON(" "),
    MALE("мужской"),
    FEMALE("женский");

    private Boolean gender;
    private String name;


    Gender(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}