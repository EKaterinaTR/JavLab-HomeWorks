package ru.itis.javalab.models;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Data
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
    @Enumerated(value = EnumType.STRING)
    private State state;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public boolean isActive() {
        return state == State.ACTIVE;
    }

    public enum State {
        ACTIVE, BANNED
    }

    public enum Role {
        USER, ADMIN
    }

    @Builder
    public User(Long id, String name, String biography,
                String linkToImage, String email, String login, String hashOfpassword, State state, Role role) {
        super(id, name, biography, linkToImage);
        this.email = email;
        this.login = login;
        this.hashOfpassword = hashOfpassword;
        this.state = state;
        this.role = role;

    }
    @Override
    public String toString(){
        return ("User:"+login + hashOfpassword);
    }


}
