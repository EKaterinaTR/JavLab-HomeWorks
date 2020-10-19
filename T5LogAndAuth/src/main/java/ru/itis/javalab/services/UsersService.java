package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

import java.util.List;
import java.util.UUID;

public interface UsersService {
    List<User> getAllUsers();
    boolean hasThisCookie(String uuid);

}

