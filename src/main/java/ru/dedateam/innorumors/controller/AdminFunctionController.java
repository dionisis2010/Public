package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.profiles.Role;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.entities.review.Review;
import ru.dedateam.innorumors.service.Data;
import ru.dedateam.innorumors.service.ModelService;

@Controller
@RequestMapping(path = "admin")
public class AdminFunctionController {

    static final String ADMIN_PASSWORD = "1jXi5-DAuVQ-ws1fL-XD5ob-J3qap-xucuD-4Xzv3-ZuMZd";
    private Data data;

    @Autowired
    public AdminFunctionController(Data data) {
        this.data = data;
    }

    @GetMapping
    public String getAdminPage(Model model) {
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
    }

    @GetMapping(path = "users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", data.users().findAllByOrderById());
        return "admin/all_users";
    }

    @GetMapping(path = "posts")
    public String getAllPosts(Model model) {
        model.addAttribute("posts", data.posts().findAllByOrderByPostedTimeDesc());
        return "admin/all_posts";
    }

    @GetMapping(path = "post/{id}")
    public String getPostWithComments(@PathVariable(name = "id") Long posId,
                                      Model model) {
        model.addAttribute("post", data.posts().findById(posId).get());
        model.addAttribute("comments", data.comments().findByPostIdOrderByPostedTimeDesc(posId));
        return "admin/post_comments";
    }

    @GetMapping(path = "comments")
    public String getAllComments(Model model) {
        model.addAttribute("comments", data.comments().findAllByOrderByPostedTimeDesc());
        return "admin/all_comments";
    }

    @PostMapping(path = "delete/user")
    public String deleteUserById(@RequestParam(name = "id") Long id) {
        User user = data.users().findById(id).get();
        if (user.getIsDeleted()) {
            user.setIsDeleted(false);
            user.setRole(Role.ROLE_USER);
        } else {
            user.setIsDeleted(true);
            user.setRole(Role.ROLE_BANED);
        }
        data.users().save(user);
        return "redirect:/admin/users";
    }

    @PostMapping(path = "delete/post")
    public String deletePostById(@RequestParam(name = "id") Long id) {
        Post post = data.posts().findById(id).get();
        if (post.getIsDeleted()) {
            post.setIsDeleted(false);
        } else {
            post.setIsDeleted(true);
        }
        data.posts().save(post);
        return "redirect:/admin/posts";
    }

    @PostMapping(path = "delete/comment")
    public String deleteCommentById(@RequestParam(name = "id") Long id) {
        Comment comment = data.comments().findById(id).get();
        if (comment.getIsDeleted()) {
            comment.setIsDeleted(false);
        } else {
            comment.setIsDeleted(true);
        }
        data.comments().save(comment);
        return "redirect:/admin/comments";
    }

    @GetMapping(path = "review")
    public String getReviews(Model model) {
        model.addAttribute("reviews", data.reviews().findAllByIsDeletedOrderByPostedTime(false));
        return "admin/all_reviews";
    }

    @PostMapping(path = "delete/review")
    public String deleteReview(@RequestParam(name = "id") Long id) {
        Review review = data.reviews().findById(id).get();
        review.setIsDeleted(true);
        data.reviews().save(review);
        return "redirect:/admin/review";
    }

}
