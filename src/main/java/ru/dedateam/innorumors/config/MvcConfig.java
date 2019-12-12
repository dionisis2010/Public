package ru.dedateam.innorumors.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/login").setViewName("logIn");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/deda").setViewName("deda");

//        registry.addViewController("/logIn").setViewName("logIn");
        registry.addViewController("/404").setViewName("404");
        registry.addViewController("/contact_us").setViewName("contact_us");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/my_post").setViewName("my_post");
        registry.addViewController("/new_post").setViewName("new_post");
        registry.addViewController("/post_user").setViewName("post_user");
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/property").setViewName("property");
        registry.addViewController("/user").setViewName("user");
        registry.addViewController("/user_info").setViewName("user_info");


    }

}
