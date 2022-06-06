package com.example.lab5.books;

public abstract class Book {

    private String name;
    private String author;

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    protected void setAuthor(String author) {
        this.author = author;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public abstract void setBookData(String name, String author, String... university);

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                '}';
    }
}
