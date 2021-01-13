package ru.itis.javalab.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User extends People {
    private String login;
    private String hashOfpassword;
//    private Long id;
//    private String name;
//    private String biography;
//    private String  linkToImage;

    @Builder(builderMethodName = "userBuilder")
    public User(Long id, String name, String biography, String linkToImage, String login, String hashOfpassword) {
        super(id, name, biography, linkToImage);
        this.login = login;
        this.hashOfpassword = hashOfpassword;
    }


}
