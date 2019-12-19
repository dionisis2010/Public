package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dedateam.innorumors.data.entities.profiles.Role;
import ru.dedateam.innorumors.service.Data;
import ru.dedateam.innorumors.service.ModelService;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.service.RatService;

@Controller
@RequestMapping(path = "user")
public class ProfileController {

    private Data data;
    private RatService ratService;

    @Autowired
    public ProfileController(Data data, RatService ratService) {
        this.data = data;
        this.ratService = ratService;
    }

    @GetMapping(path = "{id}")
    public String getUserByID(@PathVariable(name = "id") Long id,
                              Model model) {
        ModelService.putAuth(model);
        User user = data.users().findById(id).get();
        if (user.getRole().equals(Role.ROLE_BANED)) {
            CastomErrorController.setErrorDescription(model,
                    "Пользователь заблокирован",
                    "Если будете плохо себя вести и вас заблокируем");
            return CastomErrorController.ERROR_PAGE_WITH_DESCRIPTION;
        } else {
            user.setRating(ratService.countUserRating(id));
            model.addAttribute("user", user);
            model.addAttribute("countPosts", data.posts().countAllByAuthorId(id));
            model.addAttribute("countComments", data.comments().countAllByAuthorIdAndIsDeleted(id, false));
            return "user_info";
        }
    }

    @GetMapping(path = "all")
    public String getUserByID(Model model) {
        ModelService.putAuth(model);
        model.addAttribute("users", data.users().findByIsDeletedOrderByUsername(false));
        return "all_users";
    }

    @GetMapping(path = "my_posts")
    public String getMyPosts(Model model) {
        ModelService.putAuth(model);
        User user = ModelService.getCurrentUser();
        Iterable<Post> posts = data.posts().findByAuthorIdAndIsDeletedOrderByPostedTimeDesc(user.getId(), false);
        ratService.initPostsRating(posts);
        posts.forEach(post -> post.setCountComments(data.comments().countAllByPostIdAndIsDeleted(post.getId(), false)));
        model.addAttribute("posts", posts);
        return "my_posts";
    }
}
