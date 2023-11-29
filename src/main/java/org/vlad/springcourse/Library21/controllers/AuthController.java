package org.vlad.springcourse.Library21.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    // при переходе по адресу "/auth/login" браузер пользователя будет получать страничку аутентификации
    @GetMapping("/login")
    public  String loginPage() {
        // возвращаем представление со страничкой аутентификации
        return "auth/login";
    }
}
