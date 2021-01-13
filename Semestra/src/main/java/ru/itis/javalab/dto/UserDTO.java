package ru.itis.javalab.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class UserDTO {
    String login;
    String password;

    public UserDTO(String login,String password){
        this.login = login;
        this.password = password;
    }
}
