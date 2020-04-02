package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;


@Builder
@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashPassword;
    private Role role;
    private State state;
    private LocalDateTime createdAt;
    private String confirmCode;
}