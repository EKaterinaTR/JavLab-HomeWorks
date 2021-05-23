package ru.itis.javalab.accesstokenboot.rest.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.accesstokenboot.rest.dto.EmailPasswordDto;
import ru.itis.javalab.accesstokenboot.rest.dto.TokenDto;
import ru.itis.javalab.accesstokenboot.rest.models.Token;
import ru.itis.javalab.accesstokenboot.rest.models.User;
import ru.itis.javalab.accesstokenboot.rest.repositories.TokensRepository;
import ru.itis.javalab.accesstokenboot.rest.repositories.UsersRepository;

import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * 05.04.2021
 * 21. REST API
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private TokenService tokenService;

    @SneakyThrows
    @Override
    public Map<String, String> login(EmailPasswordDto emailPassword) {
        User user = usersRepository.findByEmail(emailPassword.getEmail())
                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("User not found"));
        if (passwordEncoder.matches(emailPassword.getPassword(), user.getHashPassword())) {
            return tokenService.createTokens(user);
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
