package com.example.lab5.users;

import com.example.lab5.books.Book;

import java.util.HashSet;
import java.util.Set;

public abstract class User {

    private String name;
    private String surname;
    Set<Book> books = new HashSet<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public abstract void setUserName(String name);
    public abstract void setUserSurname(String name);
    public abstract String getFullName();
}
