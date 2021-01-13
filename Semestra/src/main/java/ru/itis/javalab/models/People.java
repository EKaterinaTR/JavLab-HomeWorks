package ru.itis.javalab.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class People extends UniqueEntity {
    private String name;
    private String biography;
    private String linkToImage;

    @Builder(builderMethodName = "peopleBuilder")
    public People(Long id, String name, String biography, String linkToImage) {
        super(id);
        this.name = name;
        this.biography = biography;
        this.linkToImage = linkToImage;
    }

}
