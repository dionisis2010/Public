package ru.dedateam.innorumors.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.repositories.CommentRepo;
import ru.dedateam.innorumors.data.repositories.PostRepo;
import ru.dedateam.innorumors.data.repositories.UserRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

@Controller
@RequestMapping(path = "/post")
public class PostController {

    private UserRepo userRepo;
    private PostRepo postRepo;
    private CommentRepo commentRepo;

    @Autowired
    public PostController(UserRepo userRepo, PostRepo postRepo, CommentRepo commentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @GetMapping(path = "/create")
    public String getCreatPostPage() {
        return "new_post";
    }

    @PostMapping(path = "/create")
    public String createPost(@RequestParam(name = "title") String title,
                             @RequestParam(name = "body") String body,
                             @RequestParam(name = "isAnonymous", required = false, defaultValue = "false") Boolean isAnonymous,
                             Model model) {
        Post post = new Post();
        post.setAuthor(userRepo.findById(1L).get());
        post.setTitle(title);
        post.setBody(body);
        post.setIsAnonymous(isAnonymous);
        post.setPostedTime(LocalDateTime.now());
        model.addAttribute("post", post);
        post.setComments(new HashSet<Comment>());

        postRepo.save(post);
        return "post";
    }

    @GetMapping(path = "/{id}")
    public String getPostById(@PathVariable(name = "id") Long id,
                              Model model) {
        model.addAttribute("post", postRepo.findById(id).get());
        return "post";
    }

    @GetMapping(path = "/all")
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "index";
    }

}
