package ru.itis.handlers.websockets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.dto.MessageDto;
import ru.itis.services.ChatService;
import ru.itis.services.MessageService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@EnableWebSocket
@Component
public class WebSocketMessagesHandler extends TextWebSocketHandler {

    private static final Map<Long, WebSocketSession> sessions = new HashMap<>();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatService chatService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.put((Long) session.getAttributes().get("userId"),session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        try {
            //.getPayload() - получение сообщения в формате JSON
            String messageBody = (String) message.getPayload();
            MessageDto messageDto = objectMapper.readValue(messageBody, MessageDto.class);

            messageService.saveMessage(messageDto);

            Set<Long> userIds = chatService.getUserIdsForChatRoom(messageDto.getRoomIdentifier());

            if (!sessions.containsKey(messageDto.getUserId())) {
                sessions.put(messageDto.getUserId(), session);
            }

            for (Long userId : userIds) {
                if (sessions.containsKey(userId)) {
                    sessions.get(userId).sendMessage(message);
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }

}
