package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dedateam.innorumors.data.Views;
import ru.dedateam.innorumors.data.entities.profiles.Gender;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.UserRepo;

import javax.swing.text.View;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
public class ProfileController {

    private UserRepo userRepo;

    @Autowired
    public ProfileController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping(path = "/{id}")
    public String getUserByID(@PathVariable(name = "id") Long id,
                              Model model) {
        Optional<User> user = userRepo.findById(id);
        model.addAttribute("user", user.get());
        return Views.USER_UNFO.getNameView();
    }

    @PostMapping(path = "/add")
    public void addUser(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password,
                        @RequestParam(name = "confirm_password") String confirm_password,
                        Model model) {
//        if (password.equals(confirm_password)){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setGender(Gender.MALE);
        user.setBirthDay(LocalDateTime.now());
        user.setRegistrationTime(LocalDateTime.now());
        user.setLastLogIn(LocalDateTime.now());
        user.setRating(0);
        userRepo.save(user);
        System.out.println("USPEH");
//        }
//        model.addAttribute("users", userRepo.findAll());
//        return "all_users";
    }

    @GetMapping(path = "/all")
    public String getUserByID(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return Views.ALL_USERS.getNameView();
    }

    @PostMapping(path = "/update")
    public String updateUser(User user,
                             Model model) {
        return Views.USER_UNFO.getNameView();
    }

}
