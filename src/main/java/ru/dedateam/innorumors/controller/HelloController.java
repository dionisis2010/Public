package ru.dedateam.innorumors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping()
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, path = "deda")
    public String getDeda() {
        return "deda";
    }

    @RequestMapping(method = RequestMethod.GET, path = "post")
    public String getPost(Model model) {
        return "Innorumors/post-deatils";
    }

    @RequestMapping(method = RequestMethod.GET, path = "create-post")
    public String getCreatPostForm(){
        return "Innorumors/new_post";
    }

    @RequestMapping(method = RequestMethod.POST, path = "newpost")
    public String createPost(Model model){

        return "Innorumors/post-deatils";
    }

}
