package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dedateam.innorumors.data.Views;
import ru.dedateam.innorumors.data.entities.profiles.Gender;
import ru.dedateam.innorumors.data.entities.profiles.Role;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.CommentRepo;
import ru.dedateam.innorumors.data.repositories.PostRepo;
import ru.dedateam.innorumors.data.repositories.UserRepo;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
public class ProfileController {

    private UserRepo userRepo;
    private PostRepo postRepo;
    private CommentRepo commentRepo;

    @Autowired
    public ProfileController(UserRepo userRepo, PostRepo postRepo, CommentRepo commentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @GetMapping(path = "/{id}")
    public String getUserByID(@PathVariable(name = "id") Long id,
                              Model model) {
        Optional<User> user = userRepo.findById(id);
        model.addAttribute("user", user.get());
        return "user_info";
    }



    @GetMapping(path = "/all")
    public String getUserByID(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "all_users";
    }

    @GetMapping(path = "/my_posts")
    public String getMyPosts(Model model) {
        model.addAttribute("posts", postRepo.findByAuthorId(1L));
        return "my_posts";
    }

    @GetMapping(path = "/properties")
    public String getPropertiesPage() {
        return "property";
    }

    @PostMapping(path = "/properties")
    public String updateProfile(){
        User user = userRepo.findById(1L).get();

        return "user_info";
    }

    @PostMapping(path = "/update")
    public String updateUser(User user,
                             Model model) {
        return Views.USER_INFO.getNameView();
    }

}
