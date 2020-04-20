package ru.itis.services;

import ru.itis.dto.MessageDto;

public interface MessageService {
    void saveMessage(MessageDto messageDto);
}
