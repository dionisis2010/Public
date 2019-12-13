package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dedateam.innorumors.data.Views;
import ru.dedateam.innorumors.data.repositories.UserRepo;

@Controller
@RequestMapping(path = "/")
public class MainController {

    private UserRepo userRepo;

    @Autowired
    public MainController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping(path = "/")
    public String getHomePage() {
        return Views.INDEX.getNameView();
    }

    @GetMapping(path = "/login")
    public String getLogInPage() {
        return Views.LOGIN_PAGE.getNameView();
    }

    @GetMapping(path = "/registration")
    public String getRegistrationPage() {
        return Views.REGISTRATION_PAGE.getNameView();
    }

    @GetMapping(path = "/search")
    public String search() {
        return Views.INDEX.getNameView();
    }


}
