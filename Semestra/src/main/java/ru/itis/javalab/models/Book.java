package ru.itis.javalab.models;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class Book  {
    private Long id;
    private String name;
    private String description;
    private String[] linksToImage;
    private Integer year;
    private WriterOrTranslator writer;
    private WriterOrTranslator translator;
    private Boolean isTranslation;

}
