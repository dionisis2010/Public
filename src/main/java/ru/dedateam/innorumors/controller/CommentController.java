package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.repositories.CommentRepo;
import ru.dedateam.innorumors.data.repositories.PostRepo;
import ru.dedateam.innorumors.data.repositories.UserRepo;

@Controller
@RequestMapping(path = "/comment")
public class CommentController {
    private UserRepo userRepo;
    private PostRepo postRepo;
    private CommentRepo commentRepo;

    @Autowired
    public CommentController(UserRepo userRepo, PostRepo postRepo, CommentRepo commentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @PostMapping(path = "/add/{postId}")
    public String addComment(@RequestParam(name = "body") String body,
                             @PathVariable(name = "postId") Long postId){
        Comment comment = new Comment(body);
        comment.setAuthor(userRepo.findById(5L).get());
        comment.setPost(postRepo.findById(postId).get());

        commentRepo.save(comment);
//        return "forward:/post/"+ postId;
        return "forward:/post/"+postId;
    }
}
