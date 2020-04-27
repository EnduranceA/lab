package ru.itis.services.impl.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import ru.itis.services.interfaces.EmailService;
import ru.itis.services.interfaces.MessageGenerationService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender javaMailSender;

    @Autowired
    public MessageGenerationService messageGenerationService;

    @Override
    public void sendMessage(String subject, String text, String toEmail) {
        MimeMessagePreparator message = messageGenerationService.createMessage
                (subject, text, toEmail);
        javaMailSender.send(message);
    }

    @Override
    public void sendConfirmMessage(String confirmCode, String toEmail) {
        sendMessage("Confirm", "http://localhost:8080/confirm/" + confirmCode, toEmail);
    }

    @Override
    public void sendFileLinkMessage(String fileName, String toEmail) {
        sendMessage("File Link", "http://localhost:8080/files/" + fileName, toEmail);
    }
}
