package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dedateam.innorumors.data.Views;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.CommentRepo;
import ru.dedateam.innorumors.data.repositories.PostRepo;
import ru.dedateam.innorumors.data.repositories.UserRepo;

@Controller
@RequestMapping(path = "/")
public class MainController {

    private UserRepo userRepo;
    private PostRepo postRepo;
    private CommentRepo commentRepo;
    private Authentication authentication;

    @Autowired
    public MainController(UserRepo userRepo, PostRepo postRepo, CommentRepo commentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.authentication = SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping(path = "/")
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "all_posts";
    }

    @GetMapping(path = "/login")
    public String getLogInPage() {
        return "login_page";
    }

    @GetMapping(path = "/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping(path = "/do_registration")
    public String addUser(@RequestParam(name = "username") String username,
                          @RequestParam(name = "password") String password,
                          @RequestParam(name = "confirm_password") String confirm_password,
                          Model model) {
        if (userRepo.findByUsername(username) !=null){
            model.addAttribute("errorTitle", "Ошибка имени пользователя");
            model.addAttribute("errorDescription", "Пользователь с таким именем уже существует");
            return "error_page" ;
        }
        if (password.equals(confirm_password)) {
            User user = new User(username, password);

            userRepo.save(user);
            model.addAttribute("users", user);
            return "index";
        } else {
            model.addAttribute("errorTitle", "Ошибка рагистрации");
            model.addAttribute("errorDescription", "Пароли не совпадают");
            return "error_page" ;
        }
    }

    @GetMapping(path = "/contacts")
    public String getContactsPage() {
        return "contact_us";
    }

    @GetMapping(path = "/search")
    public String search() {
        return Views.INDEX.getNameView();
    }


}
