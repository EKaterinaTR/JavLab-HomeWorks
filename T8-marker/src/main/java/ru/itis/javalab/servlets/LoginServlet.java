package ru.itis.javalab.servlets;

import org.springframework.context.ApplicationContext;
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


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private CookiesService cookiesService;
    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        usersService = applicationContext.getBean(UsersService.class);
        cookiesService = applicationContext.getBean(CookiesService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF\\html\\login.html").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = usersService.signIn(login, password);
        if (user != null) {
            System.out.println("there2");
            cookiesService.addNewCookie(user.getId(), request.getSession().getId());
            request.getSession().setAttribute("Name",user.getFirstName() + " " + user.getLastName());
            response.addCookie(new Cookie("Auth",request.getSession().getId()));
            response.sendRedirect(request.getContextPath() + "/enter/hello");
        } else {
            System.out.println("wrong");
            response.sendRedirect(request.getContextPath() + "/login");

        }

    }
}
