package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dedateam.innorumors.config.InnoContext;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.profiles.Gender;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.CommentRepo;
import ru.dedateam.innorumors.data.repositories.PostRepo;
import ru.dedateam.innorumors.data.repositories.UserRepo;

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
        Iterable<Post> posts = postRepo.findByAuthorId(user.getId());
        model.addAttribute("posts", posts);
        return "my_posts";
    }

    @GetMapping(path = "/properties")
    public String getPropertiesPage() {
        return "property";
    }

    @PostMapping(path = "/properties")
    public String updateProfile() {
        User user = userRepo.findById(InnoContext.getCurrentUser().getId()).get();

        return "user_info";
    }

    @PostMapping(path = "/update")
    public String updateUser(@RequestParam(name = "birthDay") LocalDateTime birthDay,
                             @RequestParam(name = "gender") Gender gender,

                             Model model) {

        return "user_info";
    }

}
