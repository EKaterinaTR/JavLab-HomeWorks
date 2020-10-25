package ru.itis.javalab.servlets;

import ru.itis.javalab.services.CookiesService;

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

@WebServlet("/login2")
public class Login2Servlet extends HttpServlet {
    private CookiesService cookiesService;

    @Override
    public void init (ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.cookiesService = (CookiesService) servletContext.getAttribute("cookiesService");


    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cookiesService.addNewCookie(10L,request.getSession().getId());
        System.out.println("there2");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        //TODO:сделать ответ в пост (вход страница)
        //TODO: сделать штфрование
    }
}
