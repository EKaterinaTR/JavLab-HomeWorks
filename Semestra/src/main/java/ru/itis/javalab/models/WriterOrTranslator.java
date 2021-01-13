package ru.itis.javalab.models;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WriterOrTranslator extends People {
    List<Book> books;


    public WriterOrTranslator(Long id, String name, String biography, String linkToImage) {
        super(id, name, biography, linkToImage);
    }
}

