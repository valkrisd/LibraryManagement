package org.vlad.springcourse.Library21.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String home() {
        return "common/main";
    }

}
