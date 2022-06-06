package com.example.lab5.books;

import java.util.concurrent.ThreadLocalRandom;

public class EducationBookRu extends Book {
    ThreadLocalRandom random;

    public EducationBookRu() {
        this.random = ThreadLocalRandom.current();
    }

    @Override
    public void setBookData(String name, String author, String... university) {
        setName(name);
        setAuthor(author);
        int nextInt = random.nextInt(0, 100);
        if (nextInt < 34) {
            setName("Учебник " + name);
        } else if (nextInt < 67) {
            setName("Задачник " + name);
        } else {
            setName("Пособие " + name);
        }
    }
}
