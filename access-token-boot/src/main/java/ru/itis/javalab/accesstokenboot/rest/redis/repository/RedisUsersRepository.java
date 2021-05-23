package ru.itis.javalab.accesstokenboot.rest.redis.repository;


import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.itis.javalab.accesstokenboot.rest.redis.models.RedisUser;

/**
 * 09.04.2021
 * 52. JwtRedis
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */

public interface RedisUsersRepository extends KeyValueRepository<RedisUser, String> {
}
