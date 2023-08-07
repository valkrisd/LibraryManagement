package org.vlad.springcourse.models;

import javax.validation.constraints.*;

public class Person {
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(max = 100, message = "Name should be less than 100 characters")
    private String name;

    @Min(value = 14, message = "Age should be greater than 14")
    @Max(value = 150, message = "Age should be less than 150")
    private int age;

    @Email(message = "Valid email format example: john@mail.com")
    private String email;

    // Country, City, Index (6 digits)
    // Russia, Moscow, 123456
    @Size(max = 100, message = "Address should be less than 100 characters")
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Valid address format example: Russia, Moscow, 111111")
    private String address;

    public Person() {
    }

    public Person(int id, String name, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
