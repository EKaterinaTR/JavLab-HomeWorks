package ru.itis.javalab.models;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Data
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class User extends People {

    private String email;
    private String login;
    private String hashOfpassword;

    @ManyToMany
    @JoinTable(
            name = "accounts_favorite_books",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> favoriteBooks;
    @ManyToMany
    @JoinTable(
            name = "accounts_followed_books",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> followedBooks;

    @Builder
    public User(Long id, String name, String biography,
                String linkToImage, String email, String login, String hashOfpassword) {
        super(id, name, biography, linkToImage);
        this.email = email;
        this.login = login;
        this.hashOfpassword = hashOfpassword;

    }


}
