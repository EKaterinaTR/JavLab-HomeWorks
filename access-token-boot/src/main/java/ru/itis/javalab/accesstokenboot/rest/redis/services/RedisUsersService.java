package ru.itis.javalab.accesstokenboot.rest.redis.services;


import ru.itis.javalab.accesstokenboot.rest.models.User;

/**
 * 09.04.2021
 * 52. JwtRedis
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface RedisUsersService {
    void addTokenToUser(User user, String token);

    void addAllTokensToBlackList(User user);
}
