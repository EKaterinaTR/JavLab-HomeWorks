package ru.itis.javalab.filters;
import ru.itis.javalab.services.CookiesService;
import ru.itis.javalab.services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/enter/*")
public class AuthFilter implements Filter {
    private CookiesService cookiesService;
    private final String AUTH = "Auth";
    private final String PATH ="/login";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        this.cookiesService = (CookiesService) servletContext.getAttribute("cookiesService");


    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(AUTH.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }

        if(cookie != null && cookiesService.hasThisCookie(cookie.getValue())){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            response.sendRedirect(request.getContextPath() + PATH);
        }

    }

    @Override
    public void destroy() {

    }

}
