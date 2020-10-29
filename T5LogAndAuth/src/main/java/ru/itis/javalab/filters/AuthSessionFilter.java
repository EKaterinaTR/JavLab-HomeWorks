package ru.itis.javalab.filters;

import ru.itis.javalab.services.CookiesService;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/enter/*")
public class AuthSessionFilter implements Filter {
    private CookiesService cookiesService;
    private final String PATH = "/login2";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        this.cookiesService = (CookiesService) servletContext.getAttribute("cookiesService");

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (cookiesService.hasThisCookie(((HttpServletRequest) servletRequest).getSession().getId())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(request.getContextPath() + PATH);
        }

    }

    @Override
    public void destroy() {

    }

}

