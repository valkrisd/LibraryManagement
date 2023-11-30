package org.vlad.springcourse.Library21.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vlad.springcourse.Library21.models.Administrator;
import org.vlad.springcourse.Library21.services.RegistrationService;
import org.vlad.springcourse.Library21.util.AdministratorValidator;

import javax.validation.Valid;

@Controller
// У всех методов в контроллере адрес начинается с "/auth"
@RequestMapping("/auth")
public class AuthController {

    private final AdministratorValidator administratorValidator;
    private final RegistrationService registrationService;
    @Autowired
    public AuthController(AdministratorValidator administratorValidator, RegistrationService registrationService) {
        this.administratorValidator = administratorValidator;
        this.registrationService = registrationService;
    }


    // при переходе по адресу "/auth/login" браузер пользователя будет получать страничку аутентификации
    @GetMapping("/login")
    public  String loginPage() {
        // возвращаем представление со страничкой аутентификации
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("administrator") Administrator administrator) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("administrator") @Valid Administrator administrator,
                                      BindingResult bindingResult) {
        administratorValidator.validate(administrator, bindingResult);
        if (bindingResult.hasErrors()) return "/auth/registration";

        registrationService.register(administrator);

        return "redirect:/auth/login";
    }
}
