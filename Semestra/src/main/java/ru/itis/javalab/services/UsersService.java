package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserDTO;

public interface UsersService {
    boolean signUp(UserDTO userDTO);
    boolean signIn(UserDTO userDTO);
}
