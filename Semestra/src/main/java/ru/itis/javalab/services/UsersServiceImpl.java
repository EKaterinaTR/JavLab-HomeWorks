package ru.itis.javalab.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.dto.UserDTO;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.Optional;

public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean signUp(UserDTO userDTO) {
        Optional<User> user = usersRepository.findByLogin(userDTO.getLogin());
        System.out.println("ищу юзера");
        if (user.isPresent()) {
            System.out.println("юзер найде");
            return false;
        } else {
            System.out.println("юзер не найден, save");
            usersRepository.save(User.userBuilder()
                    .login(userDTO.getLogin())
                    .hashOfpassword(passwordEncoder.encode(userDTO.getPassword()))
                    .name(userDTO.getLogin())
                    .build());
            return true;
        }

    }

    @Override
    public boolean signIn(UserDTO userDTO) {
        //TODO:реализовать
        return false;
    }
}
