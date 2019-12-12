package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dedateam.innorumors.data.entities.profiles.Role;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.UserRepo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(path = "profile")
public class ProfileController {

    private UserRepo userRepo;

    @Autowired
    public ProfileController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping(path = "/login")
    public String getLogInPage() {
        return "logIn";
    }


    @GetMapping(path = "/registration")
    public String getRegistrationPage() {
        return "signup";
    }

    @PostMapping(path = "/registration")
    public String registration(User user, Model model){
        user.setRole(Role.USER);
        userRepo.save(user);
        model.addAttribute("user", user);
        return "user_info";
    }

    @GetMapping(path = "/get_user")
    public String getProfile(Model model, @RequestParam(name = "id") Long id) {
        model.addAttribute("user", userRepo.findById(id));
        return "user_info";
    }

    @GetMapping(path = "/get_all_users")
    public String get(Model model) {
        model.addAttribute("count", userRepo.count());
        model.addAttribute("all_users", userRepo.findAll());

//        return "user";
        return "test/all_profiles";
    }

    @DeleteMapping(path = "/delete-user")
    public String deleteUser(Model model, @RequestParam Long id) {
        userRepo.deleteById(id);
        return "redirect:profile/get_all_users";
    }


//    @GetMapping(path = "clear-base")
//    public String clearBaseUser() {
//        userRepo.deleteAll();
//        return "redirect:profile/get";
//    }
}
