package ru.itis.javalab.servlets;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.context.ApplicationContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/enter/hello")
public class UserServlets extends HttpServlet {
    Template template;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");

        //TODO: указать не абсолютный путь
        try {
            template = applicationContext.getBean(Configuration.class).getTemplate("template_hello.ftlh");
        } catch (IOException e) {
            throw  new IllegalStateException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("name", req.getSession().getAttribute("Name"));

        resp.setCharacterEncoding("UTF-8");
        PrintWriter fileWriter = resp.getWriter();
        try {
            template.process(attributes, fileWriter);
        } catch (TemplateException e) {
            new IllegalStateException(e);
        }
        fileWriter.close();
    }
}
