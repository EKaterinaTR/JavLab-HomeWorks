package ru.itis.javalab.accesstokenboot.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.accesstokenboot.rest.models.Token;
import ru.itis.javalab.accesstokenboot.rest.models.User;


import java.util.Optional;

/**
 * 05.04.2021
 * 21. REST API
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Repository
public interface TokensRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByTokenAndTypeOfToken(String token, Token.TypeOfToken typeOfToken);
    void deleteAllByUser(User user);
}
