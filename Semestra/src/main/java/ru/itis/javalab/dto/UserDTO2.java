package ru.itis.javalab.dto;

import lombok.*;
import ru.itis.javalab.models.User;

import java.util.Optional;

/**
 * id
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO2 {
    Long id;

    public static UserDTO2 from(Optional<User> user) {
        if (user.isPresent()) {
            return UserDTO2.builder().
                    id(user.get().getId()).
                    build();
        }
        return null;
    }
}
