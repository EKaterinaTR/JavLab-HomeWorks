package ru.itis.javalab.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
