package ru.itis.javalab.dto;

import lombok.*;
import ru.itis.javalab.validation.ValidPassword;

import javax.validation.constraints.Email;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String login;
    @ValidPassword(message = "{errors.password}")
    private String password;

    @Email(message = "{errors.incorrect.email}")
    private String email;





}
