package ru.dedateam.innorumors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TetController {
    @RequestMapping(method = RequestMethod.GET, path = "homepage")
    public ModelAndView getPage(@RequestParam(name = "name", required = false) String name) {
        Map map = new HashMap(){{
            put("name", name);
        }};
        return new ModelAndView("home", map);
    }

    @RequestMapping(method = RequestMethod.GET, path = "123")
    public String get123(){
        return "Innorumors/post-deatils.html";
    }
}
