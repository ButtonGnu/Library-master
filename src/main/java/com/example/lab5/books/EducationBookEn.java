package com.example.lab5.books;

import java.util.concurrent.ThreadLocalRandom;

public class EducationBookEn extends Book {
    private String lvl;
    private String university;

    public String getLvl() {
        return lvl;
    }

    public String getUniversity() {
        return university;
    }

    @Override
    public void setBookData(String name, String author, String... university) {
        setName(name);
        setAuthor(author);
        this.university = university[0];
        int nextInt = ThreadLocalRandom.current().nextInt(0, 100);
        if (nextInt % 2 == 0) {
            this.lvl = "Master";
        } else {
            this.lvl = "Bachelor";
        }
    }

}
