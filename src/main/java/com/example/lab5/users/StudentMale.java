package com.example.lab5.users;

public class StudentMale extends User {
    @Override
    public void setUserName(String name) {
        setName(name);
    }

    @Override
    public void setUserSurname(String surname) {
        setSurname(surname);
    }

    @Override
    public String getFullName() {
        return getName() + " " + getSurname();
    }
}
