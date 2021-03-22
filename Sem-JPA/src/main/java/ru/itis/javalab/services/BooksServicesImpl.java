package ru.itis.javalab.services;

import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.BookDTO;

import java.util.List;

@Service
public class BooksServicesImpl implements BookService{
    @Override
    public List<BookDTO> getSomeBooks() {
        return null;
    }

    @Override
    public List<BookDTO> getSomeLookingBooks() {
        return null;
    }
}
