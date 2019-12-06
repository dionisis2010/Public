package ru.dedateam.innorumors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping()
public class HelloController {

    @GetMapping
    public void sayHello(HttpServletResponse response) throws IOException {
        response.setStatus(200);
        response.getOutputStream().print("HELLO INNORUMORS");
    }

}
