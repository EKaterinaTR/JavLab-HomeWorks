package ru.itis.javalab.accesstokenboot.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 05.04.2021
 * 21. REST API
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Enumerated(value = EnumType.STRING)
    private TypeOfToken typeOfToken;

    public enum  TypeOfToken {
        ACCESS,
        REFRESH
    }
}
