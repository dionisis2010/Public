package ru.dedateam.innorumors.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import ru.dedateam.innorumors.data.entities.profiles.User;

public class InnoContext {



    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }
    public static void putAuth(Model model){
        model.addAttribute("auth", getCurrentUser());
    }
}
