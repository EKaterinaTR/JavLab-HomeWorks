package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.javalab.dto.UserDTO2;
import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.services.UsersService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControllerProfile {
    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String Profile(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("user", user.getUser());
        return "profile";
    }
}
