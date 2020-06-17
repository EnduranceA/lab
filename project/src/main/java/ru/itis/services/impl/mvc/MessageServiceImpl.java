package ru.itis.services.impl.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.MessageDto;
import ru.itis.models.Message;
import ru.itis.models.User;
import ru.itis.repositories.MessageRepository;
import ru.itis.repositories.UserRepository;
import ru.itis.repositories.jpa.UserRepositoryJpa;
import ru.itis.services.interfaces.MessageService;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepositoryJpa userRepository;

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Transactional
    @Override
    public void save(MessageDto messageDto) {
        Optional<User> user = userRepository.findById(messageDto.getUserId());
        if (user.isPresent()) {
            Message message = Message.builder()
                    .text(messageDto.getText())
                    .sender(user.get())
                    .answer("Expect an answer")
                    .build();
            messageRepository.save(message);
        }
    }

    @Override
    @Transactional
    public void updateMessage(MessageDto messageDto) {
        Optional<Message> messageOptional = messageRepository.find(messageDto.getUserId());
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setAnswer(messageDto.getText());
            messageRepository.update(message);
        }
    }
}
