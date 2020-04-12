package ru.itis.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.MessageDto;
import ru.itis.models.User;
import ru.itis.security.jwt.details.UserDetailsImpl;
import ru.itis.services.interfaces.MessageService;

import java.util.*;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public String getChatPage(Model model) {
        model.addAttribute("pageId", UUID.randomUUID().toString());
        return "chat";
    }
}