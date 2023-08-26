package org.vlad.springcourse.Library21.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vlad.springcourse.Library21.models.Book;
import org.vlad.springcourse.Library21.models.Person;
import org.vlad.springcourse.Library21.repositories.BooksRepository;
import org.vlad.springcourse.Library21.repositories.PeopleRepository;

import java.util.Date;
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

    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear) return booksRepository.findAll(Sort.by("year"));
        else return booksRepository.findAll();
    }

    public List<Book> findWithPagination(Integer pageNumber, Integer booksPerPage, boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(PageRequest.of(pageNumber, booksPerPage, Sort.by("year"))).getContent();
        else return booksRepository.findAll(PageRequest.of(pageNumber, booksPerPage)).getContent();
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
        Book bookToBeUpdated = booksRepository.findById(id).get();
        updatedBook.setId(id);
        // чтобы не терялась связь при обновлении (в форме же нет например поля "владелец")
        updatedBook.setOwner(bookToBeUpdated.getOwner());
        updatedBook.setLastCheckoutTime(bookToBeUpdated.getLastCheckoutTime());
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void release(int id) {
        booksRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(null);
                    book.setLastCheckoutTime(null);

                    booksRepository.save(book);
                });
    }

    @Transactional
    public void setNewBookOwner(int person_id, int id) {
        Book book = booksRepository.findById(id).orElseThrow();
        Person newOwner = peopleRepository.findById(person_id).orElseThrow();

        book.setOwner(newOwner);
        book.setLastCheckoutTime(new Date());
        booksRepository.save(book);
    }
}
