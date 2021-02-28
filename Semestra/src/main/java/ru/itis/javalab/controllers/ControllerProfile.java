package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.javalab.dto.UserDTO2;
import ru.itis.javalab.dto.UserDTOInfo;
import ru.itis.javalab.services.UsersService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControllerProfile {
    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String Profile(Model model, HttpServletRequest request) {
        model.addAttribute("user", usersService.
                getUserInfo(((UserDTO2) request.getSession().getAttribute("user")).getId()));
        return "profile";
    }
}
