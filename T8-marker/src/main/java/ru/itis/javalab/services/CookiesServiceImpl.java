package ru.itis.javalab.services;

import ru.itis.javalab.repositories.CookiesRepository;

public class CookiesServiceImpl implements CookiesService {
    private CookiesRepository cookiesRepository;

    public CookiesServiceImpl(CookiesRepository cookiesRepository) {
        this.cookiesRepository = cookiesRepository;
    }
    @Override
    public boolean hasThisCookie(String uuid) {
        return cookiesRepository.findAnyByCookie(uuid);
    }

    @Override
    public void addNewCookie(Long id, String cookie) {
        cookiesRepository.addCookieToUserById(id,cookie);
    }

}
