package ru.itis.javalab.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class People {
    public Long id;
    private String name;
    private String biography;
    private String linkToImage;

    @Builder(builderMethodName = "peopleBuilder")
    public People(Long id, String name, String biography, String linkToImage) {
        this.id = id;
        this.name = name;
        this.biography = biography;
        this.linkToImage = linkToImage;
    }

}
