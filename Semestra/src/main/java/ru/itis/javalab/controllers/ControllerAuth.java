package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.dto.UserDTO;
import ru.itis.javalab.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class ControllerAuth {


    @Autowired
    UsersService usersService;


    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public String openTheEnterPage() {
        return "enter";
    }

    @RequestMapping(value = "/enter", method = RequestMethod.POST)
    public String signIn (HttpServletRequest request, UserDTO user) {
       if(usersService.signIn(user)) {
           request.getSession().setAttribute("user",usersService.getUser(user));
           return "redirect:profile";
       }
       return "wrong";
    }

    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    public String signUpPage(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "registration";
    }

    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public String signUp(@Valid UserDTO userDTO,BindingResult bindingResult,Model model,HttpServletRequest request) {
        System.out.println(userDTO);
        System.out.println(bindingResult);
        if (!bindingResult.hasErrors()) {
            System.out.println(userDTO);
            if (usersService.signUp(userDTO)) {
                System.out.println(userDTO);
                return signIn(request, userDTO);
            }
//TODO: ошибку отображать как-то
            return "redirect:sign";
        }else {
            model.addAttribute("userDTO", userDTO);
            return "registration";
        }

    }




}
