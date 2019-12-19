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
import ru.dedateam.innorumors.data.entities.review.Review;
import ru.dedateam.innorumors.data.repositories.CommentRepo;
import ru.dedateam.innorumors.data.repositories.PostRepo;
import ru.dedateam.innorumors.data.repositories.UserRepo;
import ru.dedateam.innorumors.service.Data;
import ru.dedateam.innorumors.service.ModelService;
import ru.dedateam.innorumors.service.RatService;

import java.util.List;

@Controller
@RequestMapping(path = "admin")
public class AdminFunctionController {

    private Data data;

    @Autowired
    public AdminFunctionController(Data data) {
        this.data = data;
    }

    @GetMapping
    public String getAdminPage(Model model) {
        if (checkAccess()) {
            model.addAttribute("countUsers", data.users().count());
            model.addAttribute("countAliveUsers", data.users().countByIsDeleted(false));
            model.addAttribute("countDeletedUsers", data.users().countByIsDeleted(true));
            model.addAttribute("countPosts", data.posts().count());
            model.addAttribute("countAlivePosts", data.posts().countByIsDeleted(false));
            model.addAttribute("countDeletedPosts", data.posts().countByIsDeleted(true));
            model.addAttribute("countComments", data.comments().count());
            model.addAttribute("countAliveComments", data.comments().countByIsDeleted(false));
            model.addAttribute("countDeletedComments", data.comments().countByIsDeleted(true));
            model.addAttribute("countReviews", data.reviews().countByIsDeleted(false));

            return "admin/admin";
        } else {
            return CastomErrorController.ERROR_ACCESS;
        }
    }

    @GetMapping(path = "users")
    public String getAllUsers(Model model) {
        if (checkAccess()) {
            model.addAttribute("users", data.users().findAllByOrderById());
            return "admin/all_users";
        } else {
            return CastomErrorController.ERROR_ACCESS;
        }
    }

    @GetMapping(path = "posts")
    public String getAllPosts(Model model) {
        if (checkAccess()) {
            model.addAttribute("posts", data.posts().findAllByOrderByPostedTimeDesc());
            return "admin/all_posts";
        } else {
            return CastomErrorController.ERROR_ACCESS;
        }
    }

    @GetMapping(path = "post/{id}")
    public String getPostWithComments(@PathVariable(name = "id") Long posId,
                                      Model model) {
        if (checkAccess()) {
            model.addAttribute("post", data.posts().findById(posId).get());
            model.addAttribute("comments", data.comments().findByPostIdOrderByPostedTimeDesc(posId));
            return "admin/post_comments";
        } else {
            return CastomErrorController.ERROR_ACCESS;
        }
    }

    @GetMapping(path = "comments")
    public String getAllComments(Model model) {
        if (checkAccess()) {
            model.addAttribute("comments", data.comments().findAllByOrderByPostedTimeDesc());
            return "admin/all_comments";
        } else {
            return CastomErrorController.ERROR_ACCESS;
        }
    }

    @PostMapping(path = "delete/user")
    public String deleteUserById(@RequestParam(name = "id") Long id) {
        if (checkAccess()) {
            User user = data.users().findById(id).get();
            if (user.getIsDeleted()) {
                user.setIsDeleted(false);
            } else {
                user.setIsDeleted(true);
            }
            data.users().save(user);
            return "redirect:/admin/users";
        } else {
            return CastomErrorController.ERROR_ACCESS;
        }
    }

    @PostMapping(path = "delete/post")
    public String deletePostById(@RequestParam(name = "id") Long id) {
        if (checkAccess()) {
            Post post = data.posts().findById(id).get();
            if (post.getIsDeleted()) {
                post.setIsDeleted(false);
            } else {
                post.setIsDeleted(true);
            }
            data.posts().save(post);
            return "redirect:/admin/posts";
        } else {
            return CastomErrorController.ERROR_ACCESS;
        }
    }

    @PostMapping(path = "delete/comment")
    public String deleteCommentById(@RequestParam(name = "id") Long id) {
        if (checkAccess()) {
            Comment comment = data.comments().findById(id).get();
            if (comment.getIsDeleted()) {
                comment.setIsDeleted(false);
            } else {
                comment.setIsDeleted(true);
            }
            data.comments().save(comment);
            return "redirect:/admin/comments";
        } else {
            return CastomErrorController.ERROR_ACCESS;
        }
    }

    @GetMapping(path = "review")
    public String getReviews(Model model) {
        if (checkAccess()) {
            model.addAttribute("reviews", data.reviews().findAllByIsDeletedOrderByPostedTime(false));
            return "admin/all_reviews";
        } else {
            return CastomErrorController.ERROR_ACCESS;
        }
    }

    @PostMapping(path = "delete/review")
    public String deleteReview(@RequestParam(name = "id") Long id) {
        if (checkAccess()) {
            Review review = data.reviews().findById(id).get();
            review.setIsDeleted(true);
            data.reviews().save(review);
            return "redirect:/admin/review";
        } else {
            return CastomErrorController.ERROR_ACCESS;
        }
    }

    private static Boolean checkAccess() {
        return ModelService.getCurrentUser().getRole().equals(Role.ADMIN);
    }
}
