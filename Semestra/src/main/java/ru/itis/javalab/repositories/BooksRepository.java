package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Book;

import java.util.List;

public interface BooksRepository extends CrudRepository {
    List<Book> getSomeBooks(Long number);
    List<Book> getSomeBooksIncluded(Long number,String string);
}
