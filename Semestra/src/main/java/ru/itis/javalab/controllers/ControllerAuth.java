package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.dto.UserDTO;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;




@Controller
public class ControllerAuth {


    @Autowired
    UsersService usersService;


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


    @RequestMapping(value = "/sign", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public String signUp (@RequestBody UserDTO userDTO) {
        System.out.println("начали дто");
        System.out.println(userDTO);
        if (usersService.signUp(userDTO)){
            //usersService.signIn(userDTO);
           //TODO:решить проблему с нечтением данного джесона в js
            return "{\"same\" : false, \"l\" : \"profile\" }" ;

        }
        else {
            return "{\"same\" : true }" ;
        }

    }

//    @RequestMapping(value = "/get", method = RequestMethod.GET)
//    public String open() {
//        UserDTO userDTO = new UserDTO("ll","ll");
//        System.out.println(" начиспользую юзер сервис");
//        if (usersService.signUp(userDTO)){
//            usersService.signIn(userDTO);
//
//
//        }
//        else {
//
//        }
//        return "enter";
//    }

















}
