package ru.itis.javalab.models;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
//    @OneToMany()
//    @JoinColumn(name = "owner_id")
//    private String[] linksToImage;
    private Integer year;
    @ManyToOne
    private WriterOrTranslator writer;
    @ManyToOne
    private WriterOrTranslator translator;
    private Boolean isTranslation;

}
