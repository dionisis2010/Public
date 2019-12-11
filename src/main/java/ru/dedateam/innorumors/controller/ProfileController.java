package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dedateam.innorumors.data.repositories.UserRepo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
