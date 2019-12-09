package ru.dedateam.innorumors.controller;

import javafx.geometry.Pos;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dedateam.innorumors.data.entities.content.Post;

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
    public String createPost(Post post){
        System.out.println(post.getTitle());
        return "Innorumors/post-deatils";
    }

    @RequestMapping(method = RequestMethod.GET, path = "get")
    public String get(Post post){
        System.out.println(123);
        return post.getTitle();
    }

}
