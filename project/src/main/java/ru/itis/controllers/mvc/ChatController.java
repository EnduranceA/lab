package ru.itis.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.models.Message;
import ru.itis.models.Role;
import ru.itis.models.User;
import ru.itis.security.jwt.details.UserDetailsImpl;
import ru.itis.services.interfaces.MessageService;
import java.util.List;


@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/chat")
    @PreAuthorize("isAuthenticated()")
    public String getChatPage(Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        List<Message> messages = messageService.findAll();
        model.addAttribute("listMessages", messages);
        model.addAttribute("userId", user.getId());
        if (user.getRole().equals(Role.ADMIN)) {
            return "chat_admin";
        }
        return "chat";
    }

}