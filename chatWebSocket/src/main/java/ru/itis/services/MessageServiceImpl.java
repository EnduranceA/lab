package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.MessageDto;
import ru.itis.models.Message;
import ru.itis.models.Room;
import ru.itis.models.User;
import ru.itis.repositories.MessagesRepository;
import ru.itis.repositories.RoomRepository;
import ru.itis.repositories.UserRepository;

import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void saveMessage(MessageDto messageDto) {
        Optional<Room> roomOptional = roomRepository.find(messageDto.getRoomIdentifier());
        Optional<User> userOptional = userRepository.find(messageDto.getUserId());
        if (roomOptional.isPresent() && userOptional.isPresent()) {
            Room room = roomOptional.get();
            Message msg = Message.builder()
                    .text(messageDto.getText())
                    .user(userOptional.get())
                    .room(room)
                    .build();
            room.getMessages().add(msg);
            messagesRepository.save(msg);
        }
    }
}
