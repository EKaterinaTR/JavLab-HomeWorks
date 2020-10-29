package ru.itis.javalab.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.repositories.CookiesRepository;
import ru.itis.javalab.repositories.CookiesRepositoryJdbcImpl;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.repositories.UsersRepositoryJdbcImpl;
import ru.itis.javalab.services.CookiesService;
import ru.itis.javalab.services.CookiesServiceImpl;
import ru.itis.javalab.services.UsersService;
import ru.itis.javalab.services.UsersServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 12.10.2020
 * 08. Web Application
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */

@WebListener
public class CustomServletContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        Properties properties = new Properties();
        try {
            properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getProperty("db.url"));
        hikariConfig.setDriverClassName(properties.getProperty("db.driver.classname"));
        hikariConfig.setUsername(properties.getProperty("db.username"));
        hikariConfig.setPassword(properties.getProperty("db.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.max-pool-size")));
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        servletContext.setAttribute("dataSource", dataSource);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UsersService usersService = new UsersServiceImpl(usersRepository, passwordEncoder);

        servletContext.setAttribute("usersService", usersService);

        CookiesRepository cookiesRepository = new CookiesRepositoryJdbcImpl(dataSource);
        CookiesService cookiesService = new CookiesServiceImpl(cookiesRepository);
        servletContext.setAttribute("cookiesService", cookiesService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
