package ru.itis.javalab.accesstokenboot.rest.services;

import ru.itis.javalab.accesstokenboot.rest.dto.EmailPasswordDto;
import ru.itis.javalab.accesstokenboot.rest.dto.TokenDto;

import java.util.Map;

/**
 * 05.04.2021
 * 21. REST API
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface LoginService {
    Map<String,String> login(EmailPasswordDto emailPassword);
}
