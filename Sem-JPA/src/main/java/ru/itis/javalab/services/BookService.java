package ru.itis.javalab.services;

import ru.itis.javalab.dto.BookDTO;

import java.util.List;


public interface BookService {
    public List<BookDTO> getSomeBooks();
    public List<BookDTO> getSomeLookingBooks();
}
