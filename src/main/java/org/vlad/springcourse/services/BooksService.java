package org.vlad.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vlad.springcourse.models.Book;
import org.vlad.springcourse.models.Person;
import org.vlad.springcourse.repositories.BooksRepository;
import org.vlad.springcourse.repositories.PeopleRepository;

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

    public List<Book> findAll(boolean sortByYear) {
        if(!sortByYear) return booksRepository.findAll();
        else return booksRepository.findAll(Sort.by("year"));
    }

    public List<Book> findAll(int pageNumber, int booksPerPage) {
        return booksRepository.findAll(PageRequest.of(pageNumber, booksPerPage)).getContent();
    }

    public List<Book> findAll(int pageNumber, int booksPerPage, boolean sortByYear) {
        if(!sortByYear) return booksRepository.findAll();
        else return booksRepository.findAll(PageRequest.of(pageNumber, booksPerPage, Sort.by("year"))).getContent();
    }

    public List<Book> findByNameStartingWith(String bookName) {
        return booksRepository.findByNameStartingWith(bookName);
    }

    public Book findOne(int id) {
        Optional<Book> optionalBook = booksRepository.findById(id);
        return optionalBook.orElse(null);
    }

    public Person getCurrentBookOwner(int id) {
        Optional<Book> optionalBook = booksRepository.findById(id);
        return optionalBook.map(Book::getOwner).orElse(null);
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
        Book book = booksRepository.findById(id).orElseThrow();
        book.setOwner(null);

        booksRepository.save(book);
    }

    @Transactional
    public void setNewBookOwner(int person_id, int id) {
        Book book = booksRepository.findById(id).orElseThrow();
        Person newOwner = peopleRepository.findById(person_id).orElseThrow();

        book.setOwner(newOwner);
        booksRepository.save(book);
    }
}
