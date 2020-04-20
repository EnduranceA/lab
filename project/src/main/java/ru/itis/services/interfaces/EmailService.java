package ru.itis.services.interfaces;

public interface EmailService {
    void sendMessage(String subject, String text, String toEmail);
    void sendConfirmMessage(String confirmCode, String toEmail);
    void sendFileLinkMessage(String fileUrl, String toEmail);
}
