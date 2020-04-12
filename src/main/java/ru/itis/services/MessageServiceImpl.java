package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.MessageDto;
import ru.itis.models.Message;
import ru.itis.models.User;
import ru.itis.repositories.MessageRepository;
import ru.itis.services.interfaces.MessageService;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public void save(MessageDto messageDto, User user) {
//        Message message = Message.builder()
//                .text(messageDto.getText())
//                .currentUser(user)
//                .build();
//        messageRepository.save(message);
    }
}
