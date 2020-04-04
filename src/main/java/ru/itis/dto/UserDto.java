package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Role;
import ru.itis.models.User;
import java.util.List;
import java.util.stream.Collectors;

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
