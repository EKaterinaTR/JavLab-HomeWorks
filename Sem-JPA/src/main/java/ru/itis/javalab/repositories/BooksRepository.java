package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.javalab.models.Book;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Long> {
    @Query(value = "select * from book limit:number  ",
            nativeQuery = true)
    List<Book> getSomeBooks(@Param("number")Long number);
    @Query(value = "select * from Book book where book.name like '%'+:part+'%' limit :number ",
            nativeQuery = true)
    List<Book> getSomeBooksIncluded(@Param("number")Long number,@Param("part") String string);

}
