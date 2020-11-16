package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

import java.util.List;


public interface UsersService {
    List<User> getAllUsers();

    User signIn(String log, String password);

    boolean updatePassword(String login, String lastPassword, String newPassword);


}

