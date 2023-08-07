package org.vlad.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.vlad.springcourse.models.Person;
import org.vlad.springcourse.services.PeopleService;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {

        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        // Check if the user is editing their data and the email remains unchanged
        Person existingPerson = peopleService.findOne(person.getId());
        if (existingPerson != null) if(existingPerson.getEmail().equals(person.getEmail())) {
            return; // Skip email validation
        }
        if (peopleService.findByEmail(person.getEmail()).isPresent())
            errors.rejectValue("email", "", "This email already taken");

        if (person.getName().length() > 0) if (!Character.isUpperCase(person.getName().codePointAt(0)))
            errors.rejectValue("name", "", "Name should start with a capital letter");
    }
}
