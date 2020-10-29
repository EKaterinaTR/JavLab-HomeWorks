package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 05.10.2020
 * 08. Web Application
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from student";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_AGE = "select * from student where age = ?";
    //language=SQL
    private static final String SQL_FIND_BY_LOG =
            "select * from student  where login = ? ";
    //language=SQL
    private static final String SQL_UPDATE_PASSWORD =
            "UPDATE student set hash_password = ? where id = ? ";


    private DataSource dataSource;
    private SimpleJdbcTemplate simpleJdbcTemplate;
    private RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .hashPassword(row.getString("hash_password"))
            .login(row.getString("login"))
            .build();


    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }


    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {

        return simpleJdbcTemplate.query(SQL_FIND_ALL, userRowMapper);


    }

    @Override
    public List<User> findAllByAge(int age) {

        return simpleJdbcTemplate.query(SQL_FIND_ALL_BY_AGE, userRowMapper, age);

    }

    @Override
    public User findByLog(String log) {
        List<User> list = simpleJdbcTemplate.query(SQL_FIND_BY_LOG, userRowMapper, log);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void updatePassword(User u) {
        simpleJdbcTemplate.queryWithoutAnswer(SQL_UPDATE_PASSWORD, u.getHashPassword(), u.getId());
    }


}

