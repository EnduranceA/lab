package ru.itis.services;

import ru.itis.models.Room;

import java.util.Set;

public interface ChatService {
    Set<Long> getUserIdsForChatRoom(Long roomIdentifier);
    Room getRoom(Long identifier);
    void addUserIdToRoom(Long userId, Long roomIdentifier);
}
