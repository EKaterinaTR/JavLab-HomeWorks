package ru.itis.javalab.models;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Data
@Builder
@Entity

public class Book  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ElementCollection
    private List<String> linksToImage;
    private Integer year;
    @ManyToMany
    @JoinTable(
            name = "book_writer")
    private List<WriterOrTranslator> writers;
    //private Byte numberOfWriters;
    @ManyToMany
    @JoinTable(
            name = "book_translator")
    private List <WriterOrTranslator> translators;
    private Boolean isTranslation;
    @ManyToMany(mappedBy = "favoriteBooks")
    private List<User> liked;
    @ManyToMany(mappedBy = "followedBooks")
    private List<User> followed;

}
