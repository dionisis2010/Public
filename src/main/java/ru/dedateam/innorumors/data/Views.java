package ru.dedateam.innorumors.data;

import lombok.Getter;

@Getter
public enum Views {

    INDEX("index"),
    LOGIN_PAGE("login_page"),
    REGISTRATION_PAGE("registration"),
    USER_UNFO("user_info"),
    ALL_USERS("all_users");

    private String nameView;


    Views(String nameView) {
        this.nameView = nameView;
    }

    @Override
    public String toString() {
        return nameView;
    }
}
