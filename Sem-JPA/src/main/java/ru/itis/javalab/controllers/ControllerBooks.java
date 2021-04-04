package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.javalab.dto.BookDTO;
import ru.itis.javalab.models.Book;
import ru.itis.javalab.services.BookService;

import javax.transaction.Transactional;
import java.util.List;


@Controller
@Transactional
public class ControllerBooks {
    @Autowired
    BookService bookService;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Books(Model mode) {
        System.out.println("controller");
        List<BookDTO> book = bookService.getSomeBooks();
        System.out.println(" snova controller");
        System.out.println(book);
        mode.addAttribute("books",bookService.getSomeBooks());
        return "books";
    }
}