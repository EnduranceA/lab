package ru.itis.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GrantUserDto {
    private UserDto userDto;
    private Integer passportSeries;
    private Integer passportNumber;
    private String email;
    private Integer courseNumber;
}
