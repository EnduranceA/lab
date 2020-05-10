package ru.itis.dto;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class SignUpDto {

    @Email(message = "{errors.incorrect.email}")
    @NotNull(message = "{errors.null}")
    private String email;

    @NotNull(message = "{errors.null}")
    private String firstName;

    @NotNull(message = "{errors.null}")
    private String lastName;

    private String password;

    private String role;
}
