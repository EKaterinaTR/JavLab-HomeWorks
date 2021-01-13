package ru.itis.javalab.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.javalab.models.User;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UserRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    private static final String SQL_SAVE =
            "insert into users(login, name, biography, link_to_image, hash_password ) " +
                    "values (:login, :name, :biography, :link_to_image, :hash_password )";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from account where id = ?";
    //language=SQL
    private static final String SQL_SELECT_BY_LOGIN = "select * from account where login = ?";



    private RowMapper<User> userRowMapper = (row, i) -> User.userBuilder()
            .id(row.getLong("id"))
            .login(row.getString("login"))
            .name(row.getString("name"))
            .biography(row.getString("biography"))
            .linkToImage(row.getString("link_to_image"))
            .hashOfpassword(row.getString("hash_password"))
            .build();


    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public void save(User entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("login",entity.getLogin())
                .addValue("hash_password",entity.getHashOfpassword())
                .addValue("name",entity.getName())
                .addValue("biography",entity.getBiography())
                .addValue("link_to_image",entity.getLinkToImage());
        namedParameterJdbcTemplate.update(SQL_SAVE,parameters,keyHolder, new String[]{"id"});

        entity.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        User user;
        try {
            user = namedParameterJdbcTemplate.getJdbcTemplate().queryForObject(SQL_SELECT_BY_ID, userRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }

        return Optional.ofNullable(user);

    }


    @Override
    public List<User> findAll() {
        return null;
    }


    @Override
    public Optional<User> findByLogin(String login) {
        User user;
        try {
            user = namedParameterJdbcTemplate.getJdbcTemplate().queryForObject(SQL_SELECT_BY_ID, userRowMapper, login);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }

        return Optional.ofNullable(user);
    }
}