package org.vlad.springcourse.Library21.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vlad.springcourse.Library21.models.Book;
import org.vlad.springcourse.Library21.models.Person;
import org.vlad.springcourse.Library21.repositories.BooksRepository;
import org.vlad.springcourse.Library21.repositories.PeopleRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> optionalPerson = peopleRepository.findById(id);
        return optionalPerson.orElse(null);
    }

    public List<Book> getPersonBooks(int id) {
        Optional<Person> optionalPerson = peopleRepository.findById(id);

        if (optionalPerson.isPresent()) {
            List<Book> personBookList = optionalPerson.get().getBookList();
            Hibernate.initialize(personBookList);
            for (Book book : personBookList) checkIfExpired(book.getId());
            return personBookList;
        } else return Collections.emptyList();
    }

    public void checkIfExpired(int id) {
        Book book = booksRepository.findById(id).orElseThrow();
        long diffInMillis = Math.abs(book.getLastCheckoutTime().getTime() - new Date().getTime());
        // 864000000 millis = 10 days
        if (diffInMillis > 864000000) book.setExpired(true);
    }

    public Optional<Person> findByEmail(String email) {
        return peopleRepository.findByEmail(email);
    }

    @Transactional
    public void save(Person person) {

        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

}
