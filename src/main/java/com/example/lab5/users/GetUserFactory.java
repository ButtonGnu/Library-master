package com.example.lab5.users;

public class GetUserFactory {

    public static User getUser(UserType userType){
        switch (userType){
            case STUDENT_MALE -> {
                return new StudentMale();
            }
            case STUDENT_FEMALE -> {
                return new StudentFemale();
            }
            case PROFESSOR_FEMALE -> {
                return new ProfFemale();
            }
            case PROFESSOR_MALE -> {
                return new ProfMale();
            }
            default -> {
                throw new RuntimeException("Wrong user type.");
            }
        }
    }
}
