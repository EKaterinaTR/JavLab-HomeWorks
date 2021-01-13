package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.dto.UserDTO;
import ru.itis.javalab.models.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class ControllerAuth {


    @Autowired



    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public String openTheEnterPage() {
        return "enter";
    }

    @RequestMapping(value = "/enter", method = RequestMethod.POST)
    public String SignIn (UserDTO user) {
       if(true) {}
       return "wrong";
    }

    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    public String SignUp() {
        return "registration";
    }

    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public String SignUp (UserDTO user) {

        return "registration";
    }

    @RequestMapping(value = "/ex", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String ex (HttpServletRequest request,HttpServletResponse response) {
        System.out.println("work");
        return "{\"same\" : false }" ;
    }

    @RequestMapping(value = "/ex2", method = RequestMethod.GET)
    public String ex2() {
        return "redirect:/enter";
    }














}
