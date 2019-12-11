package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.repositories.PostRepo;

@Controller
@RequestMapping("/content")
public class ContentController {

    private PostRepo postRepo;

    @Autowired
    public ContentController(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping(path = "/deda")
    public String getDeda() {
        return "/deda";
    }

    @GetMapping(path = "/post")
    public String getPost(Post post,
                          @RequestParam(name = "body") String body,
                          Model model) {
        model.addAttribute("post", postRepo.findByBody(body));
        return "test/post";
    }


}
