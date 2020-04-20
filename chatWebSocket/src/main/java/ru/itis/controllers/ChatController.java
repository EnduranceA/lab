package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.ConnectDto;
import ru.itis.models.Room;
import ru.itis.models.User;
import ru.itis.services.ChatService;
import ru.itis.services.CookieValuesService;

@Controller
public class ChatController {

    @Autowired
    private CookieValuesService cookieValuesService;

    @Autowired
    private ChatService chatService;

    @GetMapping("/chat")
    private String getChatPage(@CookieValue(value = "AuthCookie", required = false) String cookie,
                               @RequestParam Long identifier, Model model) {
        //проверяем, что куки существуют в БД
        ru.itis.models.CookieValue cookieValue = cookieValuesService.get(cookie);
        if (cookieValue != null) {
            User user = cookieValue.getUser();
            //проверяем, что указан номер комнаты
            if (identifier != null) {
                Long userId = user.getId();
                Room room = chatService.getRoom(identifier);
                chatService.addUserIdToRoom(userId, identifier);

                model.addAttribute("roomIdentifier", room.getId());
                model.addAttribute("lastMessages", room.getMessages());
                model.addAttribute("userId", userId);
                return "chat";
            }
            return "redirect:/index";
        }
        return "redirect:/signIn";
    }

    @PostMapping("/connect")
    public ResponseEntity<Object> connectToChatRoom(@RequestBody ConnectDto connectDto){
        chatService.addUserIdToRoom(connectDto.getUserId(), connectDto.getRoomIdentifier());
        return ResponseEntity.ok().build();
    }

    //страница для перехода в комнату
    @GetMapping("/index")
    public String getIndexPage(){
        return "index";
    }
}
