package ru.itis.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.models.Role;
import ru.itis.models.User;

@Builder
@Data
public class UserDto {
    private Long id;
    private String email;
    private Role role;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
