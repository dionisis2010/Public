package ru.dedateam.innorumors.config;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.dedateam.innorumors.data.entities.profiles.User;

public class InnoContext {
    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }
}
