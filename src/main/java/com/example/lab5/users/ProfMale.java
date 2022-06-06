package com.example.lab5.users;

public class ProfMale extends User {
    private String middleName;

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

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
        return getName() + " " + this.middleName + " " + getSurname();
    }
}
