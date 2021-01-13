package ru.itis.javalab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.dto.UserDTO;
import ru.itis.javalab.models.User;


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

    @RequestMapping(value = "/regisration", method = RequestMethod.GET)
    public String SignUp() {
        return "sign_up";
    }

    @RequestMapping(value = "/regisration", method = RequestMethod.POST)
    public String SignUp (UserDTO user) {
        if(true) {}
        return "wrong";
    }







}
