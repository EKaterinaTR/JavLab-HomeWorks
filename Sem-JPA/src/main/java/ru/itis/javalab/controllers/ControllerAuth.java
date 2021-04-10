package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/wrong", method = RequestMethod.GET)
    public String openTheWrongPage() {
        return "wrong";
    }

//    @RequestMapping(value = "/enter", method = RequestMethod.POST)
//    public String signIn (HttpServletRequest request, UserDTO user) {
//       if(usersService.signIn(user)) {
//           request.getSession().setAttribute("user",usersService.getUser(user));
//           return "redirect:profile";
//       }
//       return "redirect:wrong";
//    }

    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    public String signUpPage(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "registration";
    }

    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public String signUp(@Valid UserDTO userDTO,BindingResult bindingResult,Model model,HttpServletRequest request) {
        if (!bindingResult.hasErrors()) {
            if (usersService.signUp(userDTO)) {
                System.out.println(userDTO);
                //TODO:сразу зареганым ??
                 return "redirect:sign";
            }
            return "redirect:sign?error";
        }else {
            model.addAttribute("userDTO", userDTO);
            return "registration";
        }

    }




}
