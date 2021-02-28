package ru.itis.javalab.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String login;
    private String password;
    private String email;


//    public static UserDTO from(User user) {
//        if (user == null) {
//            return null;
//        }
//        return UserDTO.builder()
//                .login(user.getFirstName())
//                .lastName(user.getLastName())
//                .build();
//    }
//
//    public static List<UserDto> from(List<User> users) {
//        return users.stream()
//                .map(UserDto::from)
//                .collect(Collectors.toList());
//    }

}
