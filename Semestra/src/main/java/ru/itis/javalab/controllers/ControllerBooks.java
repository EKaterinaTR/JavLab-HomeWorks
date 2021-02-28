package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;



@Controller
public class ControllerBooks {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Books(Model model) {

        return "books";
    }
}