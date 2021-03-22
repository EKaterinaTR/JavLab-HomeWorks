package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.javalab.models.Book;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Long> {
//    @Query("select book from Book ")
//    List<Book> getSomeBooks(@Param("number")Long number);
//    List<Book> getSomeBooksIncluded(Long number, String string);

}
