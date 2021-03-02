package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserDTO;
import ru.itis.javalab.dto.UserDTO2;
import ru.itis.javalab.dto.UserDTOInfo;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.utils.util.EmailUtil;
import ru.itis.javalab.utils.util.MailsGenerator;

import java.util.Optional;

@Service

public class UsersServiceImpl implements UsersService {
    @Autowired
    private MailsGenerator mailsGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${spring.mail.username}")
    private String from;



    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean signUp(UserDTO userDTO) {
        Optional<User> user = usersRepository.findByLogin(userDTO.getLogin());
        System.out.println("ищу юзера");
        if (user.isPresent()) {
            System.out.println("юзер найде");
            return false;
        } else {
            System.out.println("юзер не найден, save");
            User newUser = User.userBuilder()
                    .login(userDTO.getLogin())
                    .hashOfpassword(passwordEncoder.encode(userDTO.getPassword()))
                    .name(userDTO.getLogin())
                    .linkToImage("")
                    .biography("")
                    .email(userDTO.getEmail())
                    .build();
            usersRepository.save(newUser);

            String text = mailsGenerator.getMailForHello(serverUrl);
            emailUtil.sendMail(newUser.getEmail(), "Регистрация", from, text);

            return true;
        }

    }

    @Override
    public boolean signIn(UserDTO userDTO) {
       User user = usersRepository.findByLogin(userDTO.getLogin()).
                orElse(User.userBuilder().hashOfpassword(passwordEncoder.encode(""))
                        .build());

        return passwordEncoder.matches(userDTO.getPassword(),user.getHashOfpassword());

    }

    @Override
    public UserDTO2 getUser(UserDTO user) {
        return UserDTO2.from(usersRepository.findByLogin(user.getLogin()));
    }

    @Override
    public UserDTOInfo getUserInfo(Long id) {
        return UserDTOInfo.from(usersRepository.findById(id));
    }
}
