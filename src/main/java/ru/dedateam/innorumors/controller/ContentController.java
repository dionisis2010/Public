package ru.dedateam.innorumors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dedateam.innorumors.data.entities.content.Post;

@Controller
@RequestMapping("/content")
public class ContentController {



    @GetMapping(path = "/deda")
    public String getDeda(){
        return "/deda";
    }

    @GetMapping(path = "/post")
    public ModelAndView getPost(Post post,
                                @RequestParam(name = "id") Long id){
        ModelAndView modelAndView = new ModelAndView("Innorumors/post-deatils");
        modelAndView.addObject("post", post);
        return modelAndView;
    }


}
