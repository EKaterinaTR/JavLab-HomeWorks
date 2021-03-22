package ru.itis.javalab.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WriterOrTranslator extends People {
    @OneToMany
    public List<Book> books;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    public WriterOrTranslator(Long id, String name, String biography, String linkToImage) {
        super(id, name, biography, linkToImage);
    }
    @Builder(builderMethodName = "writerBuilder")
    public WriterOrTranslator(Long id, String name, String biography,
                              String linkToImage,List<Book> books) {
        super(id, name, biography, linkToImage);
        this.books = books;

    }
}

