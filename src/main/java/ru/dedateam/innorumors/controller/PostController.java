package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.CommentRepo;
import ru.dedateam.innorumors.data.repositories.PostRepo;
import ru.dedateam.innorumors.data.repositories.UserRepo;
import ru.dedateam.innorumors.service.InnoContext;
import ru.dedateam.innorumors.service.RatService;

@Controller
@RequestMapping(path = "/post")
public class PostController {

    private PostRepo postRepo;
    private CommentRepo commentRepo;
    private UserRepo userRepo;
    private RatService ratService;

    @Autowired
    public PostController(PostRepo postRepo, CommentRepo commentRepo, UserRepo userRepo, RatService ratService) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
        this.ratService = ratService;
    }

    @GetMapping(path = "/create")
    public String getCreatPostPage(Model model) {
        InnoContext.putAuth(model);
        return "new_post";
    }

    @PostMapping(path = "/create")
    public String createPost(@RequestParam(name = "title") String title,
                             @RequestParam(name = "body") String body,
                             Model model) {
        InnoContext.putAuth(model);
        User author = InnoContext.getCurrentUser();

        Post post = new Post(title, body);
        post.setAuthor(author);

        model.addAttribute("post", post);
        model.addAttribute("countComments", 0);

        postRepo.save(post);
        return "post";
    }

    @GetMapping(path = "/{id}")
    public String getPostById(@PathVariable(name = "id") Long id,
                              Model model) {

        InnoContext.putAuth(model);
        User user = InnoContext.getCurrentUser();

        Post post = postRepo.findById(id).get();
        post.setRat(ratService.countRatingPost(post.getId()));
        model.addAttribute("post", post);

        Iterable<Comment> comments = commentRepo.findAllByPostId(id);
        ratService.countAllCommentRat(comments);
        model.addAttribute("comments", comments);
        model.addAttribute("countComments", commentRepo.countAllByPostId(id));

//        model.addAttribute("postRat", ratService.countRatingPost(id));

        return "post";
    }

//    @GetMapping(path = "/all")
//    public String getAllPosts(Model model) {
//        InnoContext.putAuth(model);
//        model.addAttribute("posts", postRepo.findAllByOrderByPostedTimeDesc());
//        return "home";
//    }

}
