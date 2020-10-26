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

@WebServlet("/login2")
public class Login2Servlet extends HttpServlet {
    private CookiesService cookiesService;
    private UsersService usersService;

    @Override
    public void init (ServletConfig config) throws ServletException {
        //надо ли ловить ошибки ?
        ServletContext servletContext = config.getServletContext();
        this.cookiesService = (CookiesService) servletContext.getAttribute("cookiesService");
        this.usersService = (UsersService) servletContext.getAttribute("usersService");


    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO: сделать шифрование
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = usersService.getUserBy(login,password);
        if (user != null){
            cookiesService.addNewCookie(user.getId(),request.getSession().getId());
            System.out.println("there2");
            response.sendRedirect(request.getContextPath() + "/enter/prof");
        }
        else {
            //response.sendRedirect(request.getContextPath() + "/registration");
            System.out.println("no registration");
        }


    }
}
