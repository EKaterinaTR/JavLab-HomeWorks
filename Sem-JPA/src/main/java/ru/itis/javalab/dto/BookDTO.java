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

    public static BookDTO from (Book book){
        if(book.getIsTranslation()){
            return BookDTO.builder()
                    .name(book.getName())
                    .author(book.getWriter().getName())
                    .translator(book.getTranslator().getName())
                    .build();
        }
        return BookDTO.builder()
                .name(book.getName())
                .author(book.getWriter().getName())
                .build();
    }
}
