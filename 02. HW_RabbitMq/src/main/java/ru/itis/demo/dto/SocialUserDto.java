package ru.itis.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialUserDto {
    private UserDto userDto;
    private Integer courseNumber;
    private String instituteName;
    private Integer passportSeries;
    private Integer passportNumber;
    private String dateOfBirth;

}
