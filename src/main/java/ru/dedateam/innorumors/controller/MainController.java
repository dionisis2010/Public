package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.profiles.Role;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.entities.review.Review;
import ru.dedateam.innorumors.service.Data;
import ru.dedateam.innorumors.service.ModelService;
import ru.dedateam.innorumors.service.RatService;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class MainController {

    private RatService ratService;
    private Data data;

    @Autowired
    public MainController(RatService ratService, Data data) {
        this.ratService = ratService;
        this.data = data;
    }

    @GetMapping
    public String getIndex(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getDetails() instanceof User) {
            return "redirect:home";
        } else {
            putPostsInModel(model);
            return "index";
        }
    }

    @GetMapping(path = "/home")
    public String getHomePage(Model model) {
        ModelService.putAuth(model);
        putPostsInModel(model);
        return "home";
    }

    private void putPostsInModel(Model model) {
        Iterable<Post> posts = data.posts().findByIsDeletedOrderByPostedTimeDesc(false);
        ratService.initPostsRating(posts);
        posts.forEach(post -> post.setCountComments(data.comments().countAllByPostIdAndIsDeleted(post.getId(), false)));
        model.addAttribute("posts", posts);
    }

    @GetMapping(path = "/deda")
    public String getDeda() {
        return "deda/deda";
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
        if (username.equals("") || password.equals("") || confirm_password.equals("")) {
            CastomErrorController.setErrorDescription(model,
                    CastomErrorController.ERROR_TITLE_REGISTRATION,
                    "Заполните все поля");
            return CastomErrorController.ERROR_PAGE_WITH_DESCRIPTION;
        } else if (data.users().findByUsername(username).isPresent()) {
            CastomErrorController.setErrorDescription(model,
                    CastomErrorController.ERROR_TITLE_REGISTRATION,
                    "Пользователь с таким именем уже существует");
            return CastomErrorController.ERROR_PAGE_WITH_DESCRIPTION;
        } else {
            if (password.equals(confirm_password)) {
                User user = new User(username, password);
                data.users().save(user);
                model.addAttribute("posts", data.posts().findAll());
                return "login_page";
            } else {
                CastomErrorController.setErrorDescription(model,
                        CastomErrorController.ERROR_TITLE_REGISTRATION,
                        "Пароли не совпадают");
                return CastomErrorController.ERROR_PAGE_WITH_DESCRIPTION;
            }
        }
    }

    @GetMapping(path = "/contacts")
    public String getContactsPage(Model model) {
        ModelService.putAuth(model);
        return "contact_us";
    }

    @PostMapping(path = "/search")
    public String search(@RequestParam(name = "word") String word,
                         Model model) {
        List<Post> posts = new LinkedList<>();
        data.posts().findByIsDeletedOrderByPostedTimeDesc(false).forEach(post -> {
            if (post.getTitle().toLowerCase().contains(word.toLowerCase())) {
                posts.add(post);
            }
        });
        ratService.initPostsRating(posts);
        posts.forEach(post -> post.setCountComments(data.comments().countAllByPostIdAndIsDeleted(post.getId(), false)));
        model.addAttribute("posts", posts);
        ModelService.putAuth(model);
        return "home";
    }

    @PostMapping(path = "review")
    public String review(@RequestParam(name = "email") String email,
                         @RequestParam(name = "title") String title,
                         @RequestParam(name = "body") String body,
                         Model model) {

        if (email.equals("") || title.equals("") || body.equals("")) {
            CastomErrorController.setErrorDescription(model,
                    "ОШИБКА СОЗДАНИЯ СООБЩЕНИЯ",
                    "Заполните все поля");
            return CastomErrorController.ERROR_PAGE_WITH_DESCRIPTION;
        } else {
            Review review = new Review(ModelService.getCurrentUser(), email, title, body);
            data.reviews().save(review);
            return "deda/deda_review";
        }
    }

    @GetMapping(path = "become_admin")
    public String getBecomeAdminPage(String password) {
        return "admin/admin_become";
    }

    @PostMapping(path = "become_admin")
    public String becomeAdmin(@RequestParam(name ="admin_password") String password) {
        if (password.equals(AdminFunctionController.ADMIN_PASSWORD)) {
            User user = ModelService.getCurrentUser();
            user.setRole(Role.ROLE_ADMIN);
            data.users().save(user);
            return "redirect:/admin";
        } else {
            return CastomErrorController.ERROR;
        }
    }
}
