package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.User;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTOInfo {

//    private List<Book> favoriteBooks;
//    private List<Book> followedBooks;
    private String name;
    private String biography;
    private String linkToImage;

    public static UserDTOInfo from(Optional<User> user) {
        if (user.isPresent()) {
            User user1 = user.get();
            return UserDTOInfo.builder().
                    name(user1.getName()).
                    biography(user1.getBiography()).
                    linkToImage(user1.getLinkToImage()).
                    build();
        }
        return null;
    }
}
