package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.Room;
import ru.itis.repositories.RoomRepository;
import java.util.*;

@Component
public class ChatServiceImpl implements ChatService {

    private static final Map<Long, Set<Long>> roomUserIds = new HashMap<>();

    @Autowired
    private RoomRepository roomRepository;

    @Override
    @Transactional
    public Room getRoom(Long identifier) {
        Optional<Room> roomOptional = roomRepository.find(identifier);
        if(roomOptional.isPresent()){
            return roomOptional.get();
        } else {
            Room room = Room.builder()
                    .id(identifier)
                    .messages(new ArrayList<>())
                    .build();
            roomRepository.save(room);
            return room;
        }
    }

    @Override
    public void addUserIdToRoom(Long userId, Long roomIdentifier) {
        if(!roomUserIds.containsKey(roomIdentifier)){
            roomUserIds.put(roomIdentifier, new HashSet<>());
        }
        roomUserIds.get(roomIdentifier).add(userId);
    }

    @Override
    public Set<Long> getUserIdsForChatRoom(Long roomIdentifier) {
        return roomUserIds.get(roomIdentifier);
    }
}
