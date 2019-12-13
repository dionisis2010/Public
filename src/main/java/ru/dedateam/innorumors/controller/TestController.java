package ru.dedateam.innorumors.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dedateam.innorumors.data.entities.profiles.Gender;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.UserRepo;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/test")
public class TestController {

    private UserRepo userRepo;

    @Autowired
    public TestController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping(path = "/form")
    public String getForm() {
        return "test/add_user";
    }

    @PostMapping(path = "/form")
    public String getForm(User user,
                          Model model) {

        user.setId(15L);
        user.setGender(Gender.MALE);
        user.setLastLogIn(LocalDateTime.now());
        user.setRegistrationTime(LocalDateTime.now());
        user.setRating(150);

        model.addAttribute("user", user);
        return "test/user";
    }

}
