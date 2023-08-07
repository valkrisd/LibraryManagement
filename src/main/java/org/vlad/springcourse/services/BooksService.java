package org.vlad.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vlad.springcourse.models.Book;
import org.vlad.springcourse.models.Person;
import org.vlad.springcourse.repositories.BooksRepository;
import org.vlad.springcourse.repositories.PeopleRepository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;
    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }
    public Book findOne(int id) {
        Optional<Book> optionalBook = booksRepository.findById(id);
        return optionalBook.orElse(null);
    }
    public Person getCurrentBookOwner(int id) {
        Optional<Book> optionalBook = booksRepository.findById(id);
        return optionalBook.orElseThrow().getOwner();
    }

    @Transactional
    public void save(Book book) {

        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);

        booksRepository.save(updatedBook);
    }
    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void deleteCurrentBookOwner(int id) {
        Optional<Book> optionalBook = booksRepository.findById(id);
        optionalBook.orElseThrow().setOwner(null);
    }
    @Transactional
    public void setNewBookOwner(int person_id, int id) {
        Optional<Book> optionalBook = booksRepository.findById(id);
        optionalBook.orElseThrow().setOwner(peopleRepository.findById(person_id).orElseThrow());
    }
}
