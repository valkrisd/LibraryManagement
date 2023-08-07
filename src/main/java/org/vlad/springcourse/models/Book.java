package org.vlad.springcourse.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Book name shouldn't be empty")
    @Size(max = 100, message = "Book name should be less than 100 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Author's name shouldn't be empty")
    @Size(max = 100, message = "Author's name should be less than 100 characters")
    @Column(name = "author")
    private String author;

    @Min(value = 900, message = "Year of publishing should be greater than 900")
    @Max(value = 2100, message = "Year of publishing should be less or even than the current year")
    @Column(name = "year")
    @NotNull()
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Book(String name, String author, int year, Person owner) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.owner = owner;
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
    public Person getOwner() {
        return owner;
    }
    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
