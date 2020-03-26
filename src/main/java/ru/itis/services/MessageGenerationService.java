package ru.itis.services;

import org.springframework.mail.javamail.MimeMessagePreparator;

public interface MessageGenerationService {
    MimeMessagePreparator createMessage(String subject, String text, String email);
}
