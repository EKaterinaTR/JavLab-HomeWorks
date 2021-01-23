package ru.itis.javalab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.javalab.dto.UserDTO;

@Controller
public class ControllerProfile {
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String SignIn (UserDTO user) {
        if(true) {}
        return "wrong";
    }
}
