package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserDTO;
import ru.itis.javalab.dto.UserDTO2;
import ru.itis.javalab.dto.UserDTOInfo;


public interface UsersService {
    boolean signUp(UserDTO userDTO);
    boolean signIn(UserDTO userDTO);
    UserDTO2 getUser(UserDTO user);
    UserDTOInfo getUserInfo(Long user);

}
