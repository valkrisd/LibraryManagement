package org.vlad.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.vlad.springcourse.models.Book;

import java.time.Year;

@Component
public class BookValidator implements Validator {

    @Autowired
    public BookValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        // Perform additional validation logic
        int currentYear = Year.now().getValue();
        if (book.getYear() > currentYear) {
            errors.rejectValue("year", "", "Year of publishing should be less or even than the current year");
        }

    }
}
