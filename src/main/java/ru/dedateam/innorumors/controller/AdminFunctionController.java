package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.CommentRepo;
import ru.dedateam.innorumors.data.repositories.PostRepo;
import ru.dedateam.innorumors.data.repositories.UserRepo;

@Controller
@RequestMapping(path = "/admin")
public class AdminFunctionController {

    private UserRepo userRepo;
    private PostRepo postRepo;
    private CommentRepo commentRepo;

    @Autowired
    public AdminFunctionController(UserRepo userRepo, PostRepo postRepo, CommentRepo commentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/delete")
    public String getDeleteUserPage() {
        return "admin/delete";
    }

    @PostMapping(path = "/delete/user")
    public String deleteUserById(@RequestParam(name = "id") Long id) {
        User user = userRepo.findById(id).get();
        if (user.getIsDeleted()) {
            user.setIsDeleted(false);
        } else{
            user.setIsDeleted(true);
        }
        userRepo.save(user);
        return "all_users";
    }

    @PostMapping(path = "/delete/post")
    public String deletePostById(@RequestParam(name = "id") Long id) {
        Post post = postRepo.findById(id).get();
        if (post.getIsDeleted()) {
            post.setIsDeleted(false);
        } else{
            post.setIsDeleted(true);
        }
        postRepo.save(post);
        return "deda";
    }

    @PostMapping(path = "/delete/comment")
    public String deleteCommentById(@RequestParam(name = "id") Long id) {
        Comment comment = commentRepo.findById(id).get();
        if (comment.getIsDeleted()) {
            comment.setIsDeleted(false);
        } else{
            comment.setIsDeleted(true);
        }
        commentRepo.save(comment);
        return "deda";
    }
}
