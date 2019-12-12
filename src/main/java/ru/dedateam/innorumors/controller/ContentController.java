package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.repositories.CommentRepo;
import ru.dedateam.innorumors.data.repositories.PostRepo;
import ru.dedateam.innorumors.data.repositories.UserRepo;

@Controller
@RequestMapping("/content")
public class ContentController {


    private UserRepo userRepo;
    private PostRepo postRepo;
    private CommentRepo commentRepo;

    @Autowired
    public ContentController(UserRepo userRepo, PostRepo postRepo, CommentRepo commentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @GetMapping(path = "/deda")
    public String getDeda() {
        return "/deda";
    }

    @GetMapping(path = "/user")
    public String getUserById(@RequestParam(name = "id") Long id,
                          Model model) {
        model.addAttribute("user", userRepo.findById(id));
        return "/test/user";
    }

    @GetMapping(path = "/user1")
    public String getUserByAge(@RequestParam(name = "age") Integer age,
                          Model model) {
        model.addAttribute("user", userRepo.findByAge(age));
        return "/test/user";
    }


}
