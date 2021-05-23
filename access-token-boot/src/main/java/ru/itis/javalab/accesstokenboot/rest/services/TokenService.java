package ru.itis.javalab.accesstokenboot.rest.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;
import ru.itis.javalab.accesstokenboot.rest.models.Token;
import ru.itis.javalab.accesstokenboot.rest.models.User;

import java.util.Map;
import java.util.UUID;

@Service
public interface TokenService {
    public String createNewAccessTokenUsingRefrsh(String refreshToken) throws Throwable;
    Map<String,String> createTokens(User user);

     User getUserFromToken(String token) ;

     String getTokenFromUser(User user);
}
