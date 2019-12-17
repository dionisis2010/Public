package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dedateam.innorumors.service.InnoContext;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.profiles.Gender;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.CommentRepo;
import ru.dedateam.innorumors.data.repositories.PostRepo;
import ru.dedateam.innorumors.data.repositories.UserRepo;
import ru.dedateam.innorumors.service.DateFormater;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
public class ProfileController {

    private PostRepo postRepo;
    private CommentRepo commentRepo;
    private UserRepo userRepo;

    @Autowired
    public ProfileController(PostRepo postRepo, CommentRepo commentRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
    }

    @GetMapping(path = "/{id}")
    public String getUserByID(@PathVariable(name = "id") Long id,
                              Model model) {
        InnoContext.putAuth(model);
        Optional<User> user = userRepo.findById(id);
        model.addAttribute("user", user.get());
        model.addAttribute("countPosts", userRepo.countAllById(id));
        return "user_info";
    }

    @GetMapping(path = "/all")
    public String getUserByID(Model model) {
        InnoContext.putAuth(model);
        model.addAttribute("users", userRepo.findAll());

        return "all_users";
    }

    @GetMapping(path = "/my_posts")
    public String getMyPosts(Model model) {
        InnoContext.putAuth(model);
        User user = InnoContext.getCurrentUser();
//        Iterable<Post> posts = postRepo.findByAuthorId(user.getId());
        Iterable<Post> posts = postRepo.findByAuthorIdOrderByPostedTimeDesc(user.getId());

        model.addAttribute("posts", posts);
        return "my_posts";
    }

    @PostMapping(path = "/properties")
    public String updateProfile() {
        User user = userRepo.findById(InnoContext.getCurrentUser().getId()).get();

        return "user_info";
    }

    @GetMapping(path = "/properties")
    public String getPropertiesPage(Model model) {
        InnoContext.putAuth(model);
        return "property";
    }

    @PostMapping(path = "/update")
    public String updateUser(@RequestParam(name = "birthDay", required = false) String birthDay,
                             @RequestParam(name = "gender", required = false) Gender gender,
                             Model model) throws ParseException {

        User user = InnoContext.getCurrentUser();
//        user.setGender(gender);
        LocalDateTime b = DateFormater.parse(birthDay);
        user.setBirthDay(b);
        System.out.println(user);
        return "user_info";
    }

}
