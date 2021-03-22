package ru.itis.javalab.repositories;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.Book;
import ru.itis.javalab.models.User;
import ru.itis.javalab.models.WriterOrTranslator;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class BooksRepositoryImpl implements BooksRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    private static final String SQL_SAVE =
            "insert into users(login, name, biography, link_to_image, hash_password,email ) " +
                    "values (:login, :name, :biography, :link_to_image, :hash_password,:email )";


    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from users where id = ?";
    //language=SQL
    private static final String SQL_SELECT_BY_LOGIN = "select * from users where login = ?";



    private RowMapper<Book> userRowMapper = (row, i) -> {
        //TODO:добавить вытаскивание автора и переводчика (без списка книг должно хватить)только id?
       return Book.builder()
                .id(row.getLong("id"))
                .description(row.getString("description"))
                .isTranslation(row.getBoolean("is_translation"))
                .linksToImage((new String[0]))
                .name(row.getString("name"))
                .year(row.getInt("year"))
                .writer((WriterOrTranslator.writerBuilder()
                        .biography(row.getString("biography"))
                        .build()
                ))
                .build();
    };



    public BooksRepositoryImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Book> getSomeBooks(Long number) {
        return null;
    }

    @Override
    public List<Book> getSomeBooksIncluded(Long number, String string) {
        return null;
    }

    @Override
    public void save(Object entity) {

    }

    @Override
    public void delete(Object o) {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public Optional findById(Object o) {
        return Optional.empty();
    }

    @Override
    public List findAll() {
        return null;
    }
}
