package org.vlad.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.vlad.springcourse.models.Book;
import org.vlad.springcourse.models.Person;
import org.vlad.springcourse.services.BooksService;
import org.vlad.springcourse.services.PeopleService;
import org.vlad.springcourse.util.BookValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;
    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService, BookValidator bookValidator) {
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(@RequestParam(required = false, name = "page_number") String pageNumber,
                        @RequestParam(required = false, name = "books_per_page") String booksPerPage,
                        @RequestParam(required = false, name = "sort_by_year") String sortByYear,
                        Model model) {

        try {
            if (pageNumber != null && booksPerPage != null && sortByYear != null) {
                model.addAttribute("books", booksService.findAll(Integer.parseInt(pageNumber),
                        Integer.parseInt(booksPerPage), Boolean.parseBoolean(sortByYear)));
            } else if (pageNumber != null && booksPerPage != null) {
                model.addAttribute("books", booksService.findAll(Integer.parseInt(pageNumber),
                        Integer.parseInt(booksPerPage)));
            } else if (sortByYear != null) {
                model.addAttribute("books", booksService.findAll(Boolean.parseBoolean(sortByYear)));
            } else model.addAttribute("books", booksService.findAll());

            return "books/index";

        } catch (NumberFormatException e) {
            return "redirect:/books/error";
        }
    }

    @GetMapping("/error")
    public String showErrorPage(Model model) {
        return "common/error";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("newOwner") Person person) {
        model.addAttribute("book", booksService.findOne(id));
        model.addAttribute("person", booksService.getCurrentBookOwner(id));
        model.addAttribute("people", peopleService.findAll());

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "/books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) return "books/new";

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOne(id));

        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) return "books/edit";

        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);

        return "redirect:/books";
    }

    @PatchMapping("release/{id}")
    public String deleteCurrentBookOwner(@PathVariable("id") int id) {
        booksService.deleteCurrentBookOwner(id);

        return "redirect:/books/" + id;
    }

    @PatchMapping("set/{id}")
    public String setNewBookOwner(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        booksService.setNewBookOwner(person.getId(), id);

        return "redirect:/books/" + id;
    }

    @GetMapping("/search")
    public String searchBook(@RequestParam(name = "query", required = false) String query, Model model) {
        List<Book> bookList = booksService.findByNameStartingWith(query);

        if (!bookList.isEmpty()) {
            model.addAttribute("books", bookList);
            return "books/index";
        } else {
            model.addAttribute("error", "No books found with the search");
            return "books/index";
        }
    }
}