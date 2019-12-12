package ru.dedateam.innorumors.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {


//    @GetMapping(path = "/404")
//    public String get404() {
//        return "/404";
//    }
//
//    @GetMapping(path = "/contact_us")
//    public String getContact_us() {
//        return "/contact_us";
//    }
//
//    @GetMapping(path = "/index")
//    public String getIndex() {
//        return "/index";
//    }
//
    @GetMapping(path = "/logIn")
    public String getLogin() {
        System.out.println(123);
        return "/logIn";
    }
//
//    @GetMapping(path = "/my_post")
//    public String getMy_post() {
//        return "/my_post";
//    }
//
//    @GetMapping(path = "/new_post")
//    public String getNew_post() {
//        return "/new_post";
//    }
//
//    @GetMapping(path = "/post_user")
//    public String getPost_user() {
//        return "/post_user";
//    }
//
//    @GetMapping(path = "/property")
//    public String getProperty() {
//        return "/property";
//    }
//
//    @GetMapping(path = "/singup")
//    public String getSingup() {
//        return "/signup";
//    }
//
//    @GetMapping(path = "/user")
//    public String getUser() {
//        return "/user";
//    }
//
//    @GetMapping(path = "/user_info")
//    public String getUser_info() {
//        return "/user_info";
//    }
}
