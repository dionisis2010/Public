package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.PostRepo;
import ru.dedateam.innorumors.data.repositories.UserRepo;

@Controller
@RequestMapping(path = "/")
public class MainController {

    private UserRepo userRepo;
    private PostRepo postRepo;

    @Autowired
    public MainController(UserRepo userRepo, PostRepo postRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }

    @GetMapping(path = "/")
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "index";
    }

    @GetMapping(path = "/deda")
    public String getDeda() {
        return "deda";
    }

    @GetMapping(path = "/login")
    public String getLogInPage() {
        return "login_page";
    }

    @PostMapping(path = "/login")
    public String login(Model model) {
//        user.setLastLogIn(LocalDateTime.now());
        return "redirect:/";
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
        if (userRepo.findByUsername(username).isPresent() == false) {
            if (password.equals(confirm_password)) {
                User user = new User(username, password);

                userRepo.save(user);
                model.addAttribute("posts", postRepo.findAll());
                return "index";
            } else {
                model.addAttribute("errorTitle", "Ошибка рагистрации");
                model.addAttribute("errorDescription", "Пароли не совпадают");
                return "error_page";
            }
        } else {
            model.addAttribute("errorTitle", "Ошибка рагистрации");
            model.addAttribute("errorDescription", "Пользователь с таким именем существует");
            return "error_page";
        }
    }

    @GetMapping(path = "/contacts")
    public String getContactsPage() {
        return "contact_us";
    }

    @GetMapping(path = "/search")
    public String search() {
        return "index";
    }


}
