package ru.itis.javalab.accesstokenboot.rest.repositories;


public interface BlacklistRepository {
    void save(String token);

    boolean exists(String token);
}
