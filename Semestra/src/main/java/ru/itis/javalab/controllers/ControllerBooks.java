package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.javalab.services.BookService;


import javax.servlet.http.HttpServletRequest;
import java.util.Locale;


@Controller
public class ControllerBooks {
    @Autowired
    BookService bookService;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Books(Model mode) {


        return "books";
    }
}