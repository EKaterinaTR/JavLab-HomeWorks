package ru.itis.javalab.services;

public interface CookiesService {
    boolean hasThisCookie(String uuid);

    void addNewCookie(Long id, String cookie);
}
