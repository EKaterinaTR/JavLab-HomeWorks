package ru.itis.javalab.models;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")

public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String login;
    private String hashOfpassword;
    private String name;
    private String biography;
    private String linkToImage;
    @ManyToMany
    private List<Book> favoriteBooks;
    @ManyToMany
    private List<Book> followedBooks;




}
