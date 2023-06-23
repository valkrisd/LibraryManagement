package org.vlad.springcourse.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty(message = "Book name shouldn't be empty")
    @Size(max = 100, message = "Book name should be less than 100 characters")
    private String name;
    @NotEmpty(message = "Author's name shouldn't be empty")
    @Size(max = 100, message = "Author's name should be less than 100 characters")
    private String author;

    @Min(value = 900, message = "Year of publishing should be greater than 900")
    @Max(value = 2023, message = "Year of publishing should be less than the current year")
    private int year;

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
