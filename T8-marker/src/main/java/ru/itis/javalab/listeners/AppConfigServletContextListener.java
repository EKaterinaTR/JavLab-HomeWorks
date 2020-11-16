package ru.itis.javalab.listeners;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.javalab.config.ApplicationConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;



/**
 * 15.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@WebListener
public class AppConfigServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        servletContext.setAttribute("applicationContext", context);


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
