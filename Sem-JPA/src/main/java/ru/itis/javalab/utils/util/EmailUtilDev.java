package ru.itis.javalab.utils.util;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class EmailUtilDev implements EmailUtil {
    @Override
    public void sendMail(String to, String subject, String from, String text) {
        System.out.println("To:" + to);
        System.out.println("From :" + from);
        System.out.println("Text:" + text);

    }
}
