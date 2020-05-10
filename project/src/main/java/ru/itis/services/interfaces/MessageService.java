package ru.itis.services.interfaces;

import ru.itis.dto.MessageDto;
import ru.itis.models.Message;
import java.util.List;

public interface MessageService {
    List<Message> findAll();
    void save(MessageDto message);
    void updateMessage(MessageDto dto);
}
