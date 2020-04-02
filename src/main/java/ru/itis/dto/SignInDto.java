package ru.itis.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SignInDto {
    private String email;
    private String password;
}
