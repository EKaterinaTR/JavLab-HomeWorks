package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import javax.sql.DataSource;

public class CookiesRepositoryJdbcImpl implements CookiesRepository {
    //language=SQL
    private static final String SQL_FIND_ANY_BY_COOKIE = "select * from sd_cookie where cookie = ?";
    //language=SQL
    private static final String SQL_UPDATE_COOKIE = "delete from sd_cookie where stud_id = ?; INSERT INTO sd_cookie VALUES (?,?);";
    private DataSource dataSource;
    private SimpleJdbcTemplate simpleJdbcTemplate;
    private RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("stud_id"))
            .authCookie(row.getString("cookie"))
            .build();


    public CookiesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    @Override
    public Boolean findAnyByCookie(String uuid) {
        return !(simpleJdbcTemplate.query(SQL_FIND_ANY_BY_COOKIE, userRowMapper, uuid).isEmpty());
    }

    @Override
    public void addCookieToUserById(Long id, String cookie) {

        simpleJdbcTemplate.queryWithoutAnswer(SQL_UPDATE_COOKIE,id,id,cookie);

    }
}
