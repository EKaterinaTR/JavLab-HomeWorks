package ru.itis.javalab;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        String tableName = "account_fааа";

        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        EntityManager entityManager = new EntityManager(jdbcTemplate);
        //entityManager.createTable(tableName, User.class);
        User user = new User(10L,"ALICA","lic",true);
        entityManager.save(tableName, user);
        user = entityManager.findById(tableName, User.class, Long.class, 10L);
        System.out.println(user);




    }
}
