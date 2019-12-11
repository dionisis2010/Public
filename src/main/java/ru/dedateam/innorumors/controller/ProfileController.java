package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.UserRepo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidParameterException;

@Controller
@RequestMapping(path = "profile")
public class ProfileController {

    @Autowired
    private UserRepo userRepo;


    @GetMapping(path = "login")
    public String getLogInPage() {
        return "/Innorumors/logIn";
    }

    @GetMapping(path = "singup")
    public String getSingUpPage() {
        return "Innorumors/signup";
    }

    @PostMapping(path = "registration")
    public String login(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password,
                        @RequestParam(name = "password") String conPassword,
                        @RequestParam(name = "age") Integer age) {
        if (!conPassword.equals(password)) {
            throw new InvalidParameterException("Паролли не совпадают");
        } else {
            User user = new User(username, password, age);
            userRepo.save(user);
            return "deda";
        }
    }

    @GetMapping(path = "get-user")
    public String getProfile(Model model, @RequestParam(name = "id") Long id) {
        model.addAttribute("profile", userRepo.findById(id));
        return "test/profile";
    }

    @GetMapping(path = "get-all-users")
    public String get(Model model) {
        model.addAttribute("findedUser", userRepo.findAll());
        model.addAttribute("count", userRepo.count());

        return "/test/profile";
    }

    @DeleteMapping(path = "delete-user")
    public String deleteUser(Model model, @RequestParam Long id) {

        return "redirect:profile/get-all-users";
    }

    @PostMapping(path = "/logup")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam Integer age) {
        User user = new User(username, password, age);
        userRepo.save(user);
        return "redirect:profile/get-all-users";
    }

    @GetMapping(path = "clear-base")
    public void clearBaseUser(HttpServletResponse response) {
        userRepo.deleteAll();
        try {
            response.getWriter().println("hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
