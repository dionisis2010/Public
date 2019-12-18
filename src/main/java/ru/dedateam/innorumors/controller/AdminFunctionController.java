package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.profiles.Role;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.CommentRepo;
import ru.dedateam.innorumors.data.repositories.PostRepo;
import ru.dedateam.innorumors.data.repositories.UserRepo;
import ru.dedateam.innorumors.service.ModelService;

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

    @GetMapping(path = "/delete")
    public String getDeleteUserPage(Model model) {
        if (checkAccess()) {
            return "admin/delete";
        } else {
            return "errors/error-with_description";
        }
    }
    @GetMapping(path = "/users")
    public String getAllUsers(Model model){
        if (checkAccess()) {

            return "admin/all_users";
        } else {
            return "errors/error-with_description";
        }
    }
    @GetMapping(path = "/posts")
    public String getAllPosts(){
        if (checkAccess()) {
            return "admin/all_posts";
        } else {
            return "errors/error-with_description";
        }
    }

    @GetMapping(path = "/comments")
    public String getAllComments(){
        if (checkAccess()) {
            return "admin/all_comments";
        } else {
            return "errors/error-with_description";
        }
    }

    @PostMapping(path = "/delete/user")
    public String deleteUserById(@RequestParam(name = "id") Long id) {
        if (checkAccess()) {
            User user = userRepo.findById(id).get();
            if (user.getIsDeleted()) {
                user.setIsDeleted(false);
            } else {
                user.setIsDeleted(true);
            }
            userRepo.save(user);
            return "all_users";
        } else {
            return "errors/error-with_description";
        }
    }

    @PostMapping(path = "/delete/post")
    public String deletePostById(@RequestParam(name = "id") Long id) {
        if (checkAccess()) {
            Post post = postRepo.findById(id).get();
            if (post.getIsDeleted()) {
                post.setIsDeleted(false);
            } else {
                post.setIsDeleted(true);
            }
            postRepo.save(post);
            return "deda";
        } else {
            return "errors/error-with_description";
        }
    }

    @PostMapping(path = "/delete/comment")
    public String deleteCommentById(@RequestParam(name = "id") Long id) {
        if (checkAccess()) {
            Comment comment = commentRepo.findById(id).get();
            if (comment.getIsDeleted()) {
                comment.setIsDeleted(false);
            } else {
                comment.setIsDeleted(true);
            }
            commentRepo.save(comment);
            return "deda";
        } else {
            return "errors/error-with_description";
        }
    }

    private static Boolean checkAccess() {
        return ModelService.getCurrentUser().getRole().equals(Role.ADMIN);
    }
}
