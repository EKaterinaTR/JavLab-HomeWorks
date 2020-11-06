package ru.itis.javalab;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.javalab.repositories.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        SimpleJdbcTemplate simpleJdbcTemplate = context.getBean(SimpleJdbcTemplate.class);
        EntityManager entityManager = new EntityManager(simpleJdbcTemplate);
//        entityManager.createTable("account", User.class);
//        User user = new User(10L,"ALICA","l",true);
//        entityManager.save("account", user);
        User user = entityManager.findById("account", User.class, Long.class, 10L);
        System.out.println(user);




    }
}
