package ru.itis.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpDto {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
