package ru.itis.javalab.accesstokenboot.rest.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.accesstokenboot.rest.models.User;
import ru.itis.javalab.accesstokenboot.rest.redis.services.RedisUsersService;


import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
@Transactional
public class TokenServiceImpl implements TokenService {


    @Autowired
    private RedisUsersService redisUsersService;

    @Autowired
    Algorithm algorithm;


    @Override
    public String createNewAccessTokenUsingRefrsh(String refreshToken) throws Throwable {

        User user = getUserFromToken(refreshToken);
        String accessToken = getTokenFromUser(user);
        redisUsersService.addAllTokensToBlackList(user);
        redisUsersService.addTokenToUser(user, accessToken);
        redisUsersService.addTokenToUser(user, refreshToken);

//        tokensRepository.deleteAllByUser(user);
//        tokensRepository.save(Token.builder()
//                .token(accessToken)
//                .user(user)
//                .typeOfToken(Token.TypeOfToken.ACCESS)
//                .build());
//        tokensRepository.save(Token.builder()
//                .token(refreshToken)
//                .typeOfToken(Token.TypeOfToken.REFRESH)
//                .user(user)
//                .build());
        return accessToken;
    }


    @Override
    public Map<String, String> createTokens(User user) {

        if(user.getRedisId() == null){
            redisUsersService.addTokenToUser(user,"inizialize");
            redisUsersService.addAllTokensToBlackList(user);

        }

        String accessToken = getTokenFromUser(user);
        String refreshToken = getTokenFromUser(user);


        redisUsersService.addTokenToUser(user, accessToken);
        redisUsersService.addTokenToUser(user, refreshToken);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("AT", accessToken);
        tokens.put("RT", refreshToken);
        return tokens;
    }

    @Override
    public User getUserFromToken(String token) {
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .build().verify(token);
        User user = User.builder()
                .state(User.State.valueOf(decodedJWT.getClaim("state").asString()))
                .role(User.Role.valueOf(decodedJWT.getClaim("role").asString()))
                .firstName((decodedJWT.getClaim("first_name").asString()))
                .lastName((decodedJWT.getClaim("last_name").asString()))
                .redisId((decodedJWT.getClaim("id_redis").asString().equals("null") ?
                        null : decodedJWT.getClaim("id_redis").asString()))
                .build();
        return user;

    }

    @Override
    public String getTokenFromUser(User user) {
        return JWT.create()
                .withClaim("role", user.getRole().toString())
                .withClaim("state", user.getState().toString())
                .withClaim("first_name", user.getFirstName())
                .withClaim("last_name", user.getLastName())
                .withClaim("uuid", UUID.randomUUID().toString())
                .withClaim("id_redis", user.getRedisId())
                .sign(algorithm);
    }
}
