package org.vlad.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.vlad.springcourse.dao.PersonDAO;
import org.vlad.springcourse.models.Person;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        // Check if the user is editing their data and the email remains unchanged
        Optional<Person> existingPerson = personDAO.show(person.getId());
        if (existingPerson.isPresent() && existingPerson.get().getEmail().equals(person.getEmail())) {
            return; // Skip email validation
        }
        if (personDAO.show(person.getEmail()).isPresent())
            errors.rejectValue("email", "", "This email already taken");

        if (!Character.isUpperCase(person.getName().codePointAt(0)))
            errors.rejectValue("name", "", "Name should start with a capital letter");
    }
}
