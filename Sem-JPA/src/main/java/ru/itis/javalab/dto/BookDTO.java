package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.Book;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    String name;
    String author;
    String translator;

    public static BookDTO from(Book book) {
        if (book.getIsTranslation()) {
            return BookDTO.builder()
                    .name(book.getName())
                    .author(book.getWriters().stream()
                            .map(writer -> writer.getName())
                            .reduce((s1,s2)->s1+s2).orElse("")
                    )
                    .translator(book.getTranslators().stream()
                    .map(translator -> translator.getName())
                    .reduce((s1,s2)->s1+s2).orElse(""))
                    .build();
        }
        return BookDTO.builder()
                .name(book.getName())
                .author(book.getWriters().stream()
                        .map(writer -> writer.getName())
                        .reduce((s1,s2)->s1+s2).orElse(""))
                .build();
    }
}
