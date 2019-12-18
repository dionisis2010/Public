package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dedateam.innorumors.service.ModelService;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.CommentRepo;
import ru.dedateam.innorumors.data.repositories.PostRepo;
import ru.dedateam.innorumors.data.repositories.UserRepo;
import ru.dedateam.innorumors.service.RatService;

@Controller
@RequestMapping(path = "/user")
public class ProfileController {

    private PostRepo postRepo;
    private CommentRepo commentRepo;
    private UserRepo userRepo;
    private RatService ratService;

    @Autowired
    public ProfileController(PostRepo postRepo, CommentRepo commentRepo, UserRepo userRepo, RatService ratService) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
        this.ratService = ratService;
    }

    public ProfileController(PostRepo postRepo, CommentRepo commentRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
    }

    @GetMapping(path = "/{id}")
    public String getUserByID(@PathVariable(name = "id") Long id,
                              Model model) {
        ModelService.putAuth(model);
        User user = userRepo.findById(id).get();
        user.setRating(ratService.countUserRat(user.getId()));
        model.addAttribute("user", user);
        model.addAttribute("countPosts", postRepo.countAllByAuthorId(id));
        model.addAttribute("countComments", commentRepo.countAllByAuthorId(id));
        return "user_info";
    }

    @GetMapping(path = "/all")
    public String getUserByID(Model model) {
        ModelService.putAuth(model);
        model.addAttribute("users", userRepo.findAll());
        return "all_users";
    }

    @GetMapping(path = "/my_posts")
    public String getMyPosts(Model model) {
        ModelService.putAuth(model);
        User user = ModelService.getCurrentUser();
        Iterable<Post> posts = postRepo.findByAuthorIdOrderByPostedTimeDesc(user.getId());
        ratService.countAllPostRat(posts);
        posts.forEach(post -> post.setCountComments(commentRepo.countAllByPostId(post.getId())));
        model.addAttribute("posts", posts);
        return "my_posts";
    }
}
