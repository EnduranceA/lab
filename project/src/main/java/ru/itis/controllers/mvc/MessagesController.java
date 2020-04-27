package ru.itis.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.MessageDto;
import ru.itis.models.Message;
import ru.itis.services.interfaces.MessageService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MessagesController {

    private static final Map<Long, List<MessageDto>> messages = new HashMap<>();

    @Autowired
    private MessageService messageService;

    // получили сообщение от какой-либо страницы - мы его разошлем во все другие страницы
    @PostMapping("/messages")
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageDto messageDto) {
        messageService.save(messageDto);
        // если сообщений с этой или для этой страницы еще не было
        // полученное сообщение добавляем для всех открытых страниц нашего приложения
        for (List<MessageDto> pageMessages : messages.values()) {
            // перед тем как положить сообщение для какой-либо страницы
            // мы список сообщений блокируем
            synchronized (pageMessages) {
                // добавляем сообщение
                pageMessages.add(messageDto);
                // говорим, что другие потоки могут воспользоваться этим списком
                pageMessages.notifyAll();
            }
        }
        return ResponseEntity.ok().build();
    }

    // получить все сообщения для текущего запроса
    @GetMapping("/messages")
    public ResponseEntity<List<MessageDto>> getMessagesForPage(@RequestParam("userId") Long userId) {
        try {
            if (!messages.containsKey(userId)) {
                // добавляем эту страницу в Map-у страниц
                messages.put(userId, new ArrayList<>());
            }
            // получили список сообщений для страницы и заблокировали его
            synchronized (messages.get(userId)) {
                // если нет сообщений уходим в ожидание
                if (messages.get(userId).isEmpty()) {
                    messages.get(userId).wait();
                }
            }
            // если сообщения есть - то кладем их в новых список
            List<MessageDto> response = new ArrayList<>(messages.get(userId));
            // удаляем сообщения из мапы
            messages.get(userId).clear();
            return ResponseEntity.ok(response);
        } catch (InterruptedException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/messages")
    public ResponseEntity<Object> updateAnswer(@RequestBody MessageDto dto) {
        messageService.updateMessage(dto);
        return ResponseEntity.ok().build();
    }
}
