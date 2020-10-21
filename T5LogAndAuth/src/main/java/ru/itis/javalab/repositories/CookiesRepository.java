package ru.itis.javalab.repositories;

public interface CookiesRepository {
    Boolean findAnyByCookie(String uuid);
    void addCookieToUserById(Long id, String cookie);

}
