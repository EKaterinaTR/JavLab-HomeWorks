package ru.itis.javalab.accesstokenboot.rest.redis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.accesstokenboot.rest.models.User;
import ru.itis.javalab.accesstokenboot.rest.redis.models.RedisUser;
import ru.itis.javalab.accesstokenboot.rest.redis.repository.RedisUsersRepository;
import ru.itis.javalab.accesstokenboot.rest.repositories.UsersRepository;
import ru.itis.javalab.accesstokenboot.rest.services.JwtBlacklistService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RedisUsersServiceImpl implements RedisUsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtBlacklistService blacklistService;

    @Autowired
    private RedisUsersRepository redisUsersRepository;

    @Override
    public void addTokenToUser(User user, String token) {
        String redisId = user.getRedisId();

        RedisUser redisUser;
        if (redisId != null) {
            redisUser = redisUsersRepository.findById(redisId).orElseThrow(IllegalArgumentException::new);
            if (redisUser.getTokens() == null) {
                redisUser.setTokens(new ArrayList<>());
            }
            redisUser.getTokens().add(token);
            redisUsersRepository.save(redisUser);
        } else {
            redisUser = RedisUser.builder()
                    .userId(user.getId())
                    .tokens(Collections.singletonList(token))
                    .build();
            redisUsersRepository.save(redisUser);
            user.setRedisId(redisUser.getId());
            usersRepository.save(user);
        }
    }

    @Override
    public void addAllTokensToBlackList(User user) {
        if (user.getRedisId() != null) {
            RedisUser redisUser = redisUsersRepository.findById(user.getRedisId())
                    .orElseThrow(IllegalArgumentException::new);

            List<String> tokens = redisUser.getTokens();
            for (String token : tokens) {
                blacklistService.add(token);
            }
            redisUser.getTokens().clear();
            redisUsersRepository.save(redisUser);
        }
    }
}
