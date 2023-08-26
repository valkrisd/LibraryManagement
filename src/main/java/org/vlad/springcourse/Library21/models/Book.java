package org.vlad.springcourse.Library21.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

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

    @Column(name = "last_checkout_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastCheckoutTime;

    @Transient
    private boolean isExpired;

    public Book(String name, String author, int year) {
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
    public Person getOwner() {
        return owner;
    }
    public void setOwner(Person owner) {
        this.owner = owner;
    }
    public Date getLastCheckoutTime() {
        return lastCheckoutTime;
    }
    public void setLastCheckoutTime(Date lastCheckoutTime) {
        this.lastCheckoutTime = lastCheckoutTime;
    }
    public boolean isExpired() {
        return isExpired;
    }
    public void setExpired(boolean expired) {
        isExpired = expired;
    }
}
