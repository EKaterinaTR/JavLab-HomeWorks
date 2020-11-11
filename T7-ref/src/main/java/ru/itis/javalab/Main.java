package ru.itis.javalab;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        EntityManager entityManager = new EntityManager(jdbcTemplate);
        entityManager.createTable("account_f", User.class);
        User user = new User(10L,"ALICA","ll",true);
        entityManager.save("account_f", user);
        user = entityManager.findById("account_f", User.class, Long.class, 10L);
        System.out.println(user);




    }
}
