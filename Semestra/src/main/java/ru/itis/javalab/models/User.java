package ru.itis.javalab.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class User extends People {
    private String email;
    private String login;
    private String hashOfpassword;
    private List<Book> favoriteBooks;
    private List<Book> followedBooks;
//    private Long id;
//    private String name;
//    private String biography;
//    private String  linkToImage;

    @Builder(builderMethodName = "userBuilder")
    public User(Long id, String name, String biography, String linkToImage, String login, String hashOfpassword,String email) {
        super(id, name, biography, linkToImage);
        this.login = login;
        this.hashOfpassword = hashOfpassword;
        this.email = email;
    }


}
