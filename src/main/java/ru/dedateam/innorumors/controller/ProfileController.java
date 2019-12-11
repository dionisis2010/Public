package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.UserRepo;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(path = "profile")
public class ProfileController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping(path = "get-user")
    public String get(Model model, @RequestParam Long id){
        model.addAttribute("findedUser", userRepo.findById(id));
        return "/test/profile";
    }
    @GetMapping(path = "login")
    public String getLogInPAge(){
        return "/test/login";
    }

    @GetMapping(path = "get-all-users")
    public String get(Model model){
        model.addAttribute("findedUser", userRepo.findAll());
        model.addAttribute("count", userRepo.count());

        return "/test/profile";
    }

    @DeleteMapping(path = "delete-user")
    public String deleteUser(Model model, @RequestParam Long id){

        return "redirect:profile/get-all-users";
    }

    @PostMapping(path = "/logup")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam boolean gender,
                        @RequestParam Integer age) {
        User user = new User(username, password, gender, age);
        userRepo.save(user);
        return "redirect:profile/get-all-users";
    }

    @GetMapping(path = "clear-base")
    public void clearBaseUser(HttpServletResponse response){
        userRepo.deleteAll();
        try {
            response.getWriter().println("hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
