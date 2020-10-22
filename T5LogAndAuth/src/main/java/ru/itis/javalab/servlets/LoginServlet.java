package ru.itis.javalab.servlets;

import ru.itis.javalab.models.User;
import ru.itis.javalab.services.CookiesService;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private CookiesService cookiesService;

    @Override
    public void init (ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.cookiesService = (CookiesService) servletContext.getAttribute("cookiesService");


    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID uuid = new UUID(99,2222);
        String sUUID = uuid.randomUUID().toString();
        Cookie cookie = new Cookie("Auth",sUUID);
        response.addCookie(cookie);

        cookiesService.addNewCookie(10L,sUUID);

        System.out.println("there");

    }

}
