package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.PostRepo;
import ru.dedateam.innorumors.data.repositories.UserRepo;

@Controller
public class PostController {

    UserRepo userRepo;
    PostRepo postRepo;

    @Autowired
    public PostController(UserRepo userRepo, PostRepo postRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }

    @GetMapping(path = "create")
    public String getAddUserForm(){
        return "test/create";
    }


    @PostMapping(path = "add_user")
    public String addUser(@RequestParam String username,
                        @RequestParam String password,
//                        @RequestParam Boolean gender,
                        @RequestParam Integer age) {
        User user = new User(username, password, true, age);
        userRepo.save(user);
        return "deda";
    }

    @GetMapping(path = "get_all_users")
    public String getAllUsers(Model model){
        model.addAttribute("count", userRepo.count());
        model.addAttribute("all_users", userRepo.findAll());
        return "test/all_profiles";
    }

    @PostMapping(path = "add_post")
    public String addPost(@RequestParam String title,
                          @RequestParam String body){
        postRepo.save(new Post(userRepo.findById(1L).get(), title, body, false));
        return "deda";
    }

    @GetMapping(path = "get_all_posts")
    public String getAllPosts(Model model){
        model.addAttribute("count", postRepo.count());
        model.addAttribute("all_posts", postRepo.findAll());
        return "test/all_posts";
    }

}
