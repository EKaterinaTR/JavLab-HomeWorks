package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import java.util.List;
import java.util.UUID;

public interface UsersRepository extends CrudRepository<User> {
    List<User> findAllByAge (int age);
    User findByLog (String log);
    void updatePassword(User u);

}
