package ru.itis.javalab.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;

/**
 * 05.10.2020
 * 08. Web Application
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public User signIn(String log, String password) {
        User user = usersRepository.findByLog(log);
        //System.out.println(user == null);
        if (user != null && passwordEncoder.matches(password, user.getHashPassword())) {
            return user;
        }
//        System.out.println(user);
//        if(user != null && user.getHashPassword().equals(password)){
//            return user;
//        }
        return null;
    }

    @Override
    public boolean updatePassword(String login, String lastPassword, String newPassword) {
        User user = signIn(login, lastPassword);
        if (user != null) {
            user.setHashPassword(passwordEncoder.encode(newPassword));
            usersRepository.updatePassword(user);
            return true;
        }
        return false;
    }

}
