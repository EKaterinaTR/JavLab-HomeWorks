package ru.itis.javalab.models;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Book extends UniqueEntity {
    private String name;
    private String biography;
    private String linkToImage;

    @Builder(builderMethodName = "bookBuilder")
    public Book(Long id, String name, String biography, String linkToImage) {
        super(id);
        this.name = name;
        this.biography = biography;
        this.linkToImage = linkToImage;
    }
}
