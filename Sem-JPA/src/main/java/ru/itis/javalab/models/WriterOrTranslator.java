package ru.itis.javalab.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class WriterOrTranslator extends People {
    @ManyToMany(mappedBy = "writers")
    public List<Book> writtenBooks;
    @ManyToMany(mappedBy = "translators")
    public List<Book> translatedBooks;

    public WriterOrTranslator() {
        super();
    }

    public WriterOrTranslator(Long id, String name, String biography, String linkToImage) {
        super(id, name, biography, linkToImage);
    }

    @Builder(builderMethodName = "writerBuilder")
    public WriterOrTranslator(Long id, String name, String biography,
                              String linkToImage, List<Book> writtenBooks, List<Book> translatedBooks) {
        super(id, name, biography, linkToImage);
        this.translatedBooks = translatedBooks;
        this.writtenBooks = writtenBooks;
    }
}

