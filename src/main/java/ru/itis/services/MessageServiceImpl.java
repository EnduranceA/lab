package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.MessageDto;
import ru.itis.models.Message;
import ru.itis.models.User;
import ru.itis.repositories.MessageRepository;
import ru.itis.repositories.UserRepository;
import ru.itis.services.interfaces.MessageService;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Transactional
    @Override
    public void save(MessageDto messageDto) {
        Optional<User> user = userRepository.find(messageDto.getUserId());
        if (user.isPresent()) {
            Message message = Message.builder()
                    .text(messageDto.getText())
                    .sender(user.get())
                    .build();
            messageRepository.save(message);
        }

    }
}
