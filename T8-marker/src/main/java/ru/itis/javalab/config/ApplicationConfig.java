package ru.itis.javalab.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
//import freemarker.cache.FileTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;

/**
 * 29.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Configuration
@PropertySource("classpath:properties\\db.properties")
@ComponentScan(basePackages = "ru.itis.javalab")
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @Bean
    public UsersService usersService() {
        return new UsersServiceImpl(usersRepository(), passwordEncoder());
    }

    @Bean
    public CookiesService cookiesService() {
        return new CookiesServiceImpl(cookiesRepository());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UsersRepository usersRepository() {
        return new UsersRepositoryJdbcImpl(dataSource());
    }

    @Bean
    public CookiesRepository cookiesRepository() {
        return new CookiesRepositoryJdbcImpl(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("db.url"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.hikari.max-pool-size")));
        hikariConfig.setUsername(environment.getProperty("db.username"));
        hikariConfig.setPassword(environment.getProperty("db.password"));
        hikariConfig.setDriverClassName(environment.getProperty("db.driver.classname"));
        return hikariConfig;
    }

    @Bean
    public freemarker.template.Configuration freemarkerTemplateConfiguration()
    {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(fileTemplateLoader());
        return configuration;
    }

    @Bean
    public FileTemplateLoader fileTemplateLoader(){
        try {
            return new FileTemplateLoader(file());
        } catch (IOException e) {
            throw  new IllegalStateException(e);
        }
    }

    @Bean
    public File file(){
        return (new File("C:\\JLab\\T8-marker\\src\\main\\webapp\\WEB-INF\\ftlh"));
    }



}

