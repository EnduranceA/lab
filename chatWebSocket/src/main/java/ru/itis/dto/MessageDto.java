package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    //указываем, какой комнате принадлежит сообщение
    private Long roomIdentifier;
    private Long userId;
    private String text;
}
