package org.vlad.springcourse.Library21.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.vlad.springcourse.Library21.models.Administrator;
import org.vlad.springcourse.Library21.models.Person;
import org.vlad.springcourse.Library21.services.AdministratorsService;

@Service
public class AdministratorValidator implements Validator {

    private final AdministratorsService administratorsService;

    @Autowired
    public AdministratorValidator(AdministratorsService administratorsService) {
        this.administratorsService = administratorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Administrator administrator = (Administrator) target;
        if (administratorsService.findByUsername(administrator.getUsername()).isPresent()) {
            errors.rejectValue("username", "", "User with this name already exists");
        }
    }
}
