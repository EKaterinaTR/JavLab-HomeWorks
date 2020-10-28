package ru.itis.javalab.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class User {
    Long id;
    String firstName;
    String lastName;
    Integer age;
    String login;
    String hashPassword;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", login='" + login + '\'' +
                ", password='" + hashPassword + '\'' +
                '}';
    }
}
