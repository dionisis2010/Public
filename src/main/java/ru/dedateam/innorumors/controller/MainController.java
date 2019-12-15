package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    public MainController(UserRepo userRepo, PostRepo postRepo, CommentRepo commentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
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

    @PostMapping(path = "/login")
    public String login(User user, Model model) {
        return "test/user";
    }

    @GetMapping(path = "/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String addUser(@RequestParam(name = "username") String username,
                          @RequestParam(name = "password") String password,
                          @RequestParam(name = "confirm_password") String confirm_password,
                          Model model) {
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
