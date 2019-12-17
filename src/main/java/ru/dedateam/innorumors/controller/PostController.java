package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.CommentRepo;
import ru.dedateam.innorumors.data.repositories.PostRepo;
import ru.dedateam.innorumors.data.repositories.UserRepo;

@Controller
@RequestMapping(path = "/post")
public class PostController {

    private PostRepo postRepo;
    private CommentRepo commentRepo;
    private UserRepo userRepo;

    @Autowired
    public PostController(PostRepo postRepo, CommentRepo commentRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
    }

    @GetMapping(path = "/create")
    public String getCreatPostPage() {
        return "new_post";
    }

    @PostMapping(path = "/create")
    public String createPost(@RequestParam(name = "title") String title,
                             @RequestParam(name = "body") String body,
                             Model model) {
        Post post = new Post(title, body);
        post.setAuthor(userRepo.findById(1L).get());

        model.addAttribute("post", post);
        model.addAttribute("countComments", 0);

        postRepo.save(post);
        return "post";
    }

    @GetMapping(path = "/{id}")
    public String getPostById(@PathVariable(name = "id") Long id,
                              Model model) {
        model.addAttribute("post", postRepo.findById(id).get());
        model.addAttribute("post", postRepo.findById(id).get());
        model.addAttribute("comments", commentRepo.findAllByPostId(id));
        model.addAttribute("countComments", commentRepo.countAllByPostId(id));
        return "post";
    }

    @GetMapping(path = "/all")
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "home";
    }

}
