package ru.itis.javalab.servlets;

import ru.itis.javalab.models.User;
import ru.itis.javalab.services.CookiesService;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/change_password")
public class ChangePassword extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init (ServletConfig config) throws ServletException {
        //надо ли ловить ошибки ?
        ServletContext servletContext = config.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");


    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/change_password.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String lastPassword = request.getParameter("last_password");
        String password = request.getParameter("password");
        if (usersService.updatePassword(login,lastPassword,password)){
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
        else {
            //response.sendRedirect(request.getContextPath() + "/registration");
            System.out.println("login or password are uncorrected");
        }


    }
}
