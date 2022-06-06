package com.example.lab5.users;

public class ProfFemale extends User {
    private String middleName;

    public void setMiddleName(String middleName) {
        this.middleName = middleName.substring(0, middleName.length() - 3) + "вна";
    }

    @Override
    public void setUserName(String name) {
        setName(name);
    }

    @Override
    public void setUserSurname(String surname) {
        if (surname.endsWith("в") || surname.endsWith("н")) {
            setSurname(surname + "а");
        } else if (surname.endsWith("ий")) {
            setSurname(surname.substring(0, surname.length() - 2) + "ая");
        } else {
            setSurname(surname);
        }
    }

    @Override
    public String getFullName() {
        return getName() + " " + this.middleName + " " + getSurname();
    }
}
