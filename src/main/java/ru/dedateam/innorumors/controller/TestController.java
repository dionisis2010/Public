package ru.dedateam.innorumors.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping(path = "/404")
    public String get404() {
        return "/Innorumors/404";
    }

    @GetMapping(path = "/contact_us")
    public String getContact_us() {
        return "/Innorumors/contact_us";
    }

    @GetMapping(path = "/index")
    public String getIndex() {
        return "/Innorumors/index";
    }

    @GetMapping(path = "/login")
    public String getLogin() {
        return "/Innorumors/logIn";
    }

    @GetMapping(path = "/my_post")
    public String getMy_post() {
        return "/Innorumors/my_post";
    }

    @GetMapping(path = "/new_post")
    public String getNew_post() {
        return "/Innorumors/new_post";
    }

    @GetMapping(path = "/post_user")
    public String getPost_user() {
        return "/Innorumors/post_user";
    }

    @GetMapping(path = "/property")
    public String getProperty() {
        return "/Innorumors/property";
    }

    @GetMapping(path = "/singup")
    public String getSingup() {
        return "/Innorumors/signup";
    }

    @GetMapping(path = "/user")
    public String getUser() {
        return "/Innorumors/user";
    }

    @GetMapping(path = "/user_info")
    public String getUser_info() {
        return "/Innorumors/user_info";
    }

    @GetMapping(path = "/deda")
    public String getDeda() {
        return "/deda";
    }
}
