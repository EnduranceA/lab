package ru.itis.services.interfaces;

import org.springframework.mail.javamail.MimeMessagePreparator;

public interface MessageGenerationService {
    MimeMessagePreparator createMessage(String subject, String text, String email);
}
