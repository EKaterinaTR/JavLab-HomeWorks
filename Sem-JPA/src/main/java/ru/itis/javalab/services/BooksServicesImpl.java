package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.BookDTO;
import ru.itis.javalab.models.Book;
import ru.itis.javalab.repositories.BooksRepository;

import java.util.List;

@Service
public class BooksServicesImpl implements BookService {

    @Autowired
    BooksRepository booksRepository;

    @Override
    public List<BookDTO> getSomeBooks() {
        List<Book> books = booksRepository.getSomeBooks(100L);
        List<BookDTO> bookDTO = BookDTO.from(books);

        return BookDTO.from(booksRepository.getSomeBooks(100L));
    }

    @Override
    public List<BookDTO> getSomeLookingBooks() {
        return null;
    }
}
