package com.example.lab5.books;

public class GetBookFactory {

    public static Book createBook(BookType bookType) {
        switch (bookType) {
            case FICTION_EN -> {
                return new FictionBookEn();
            }
            case EDUCATION_EN -> {
                return new EducationBookEn();
            }
            case FICTION_RU -> {
                return new FictionBookRu();
            }
            case EDUCATION_RU -> {
                return new EducationBookRu();
            }
            default -> throw new RuntimeException("Book type not found.");
        }
    }
}
