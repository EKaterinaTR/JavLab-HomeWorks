package ru.itis.javalab.accesstokenboot.rest.security.details;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.javalab.accesstokenboot.rest.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.javalab.accesstokenboot.rest.models.Token;
import ru.itis.javalab.accesstokenboot.rest.repositories.TokensRepository;
import ru.itis.javalab.accesstokenboot.rest.services.TokenService;


/**
 * 13.03.2021
 * 03. Spring Security
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Component("tokenUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {




    @Autowired
    TokenService tokenService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        return new UserDetailsImpl(tokenService.getUserFromToken(token));
    }
}
