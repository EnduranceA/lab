package ru.itis.services.interfaces;

import ru.itis.models.User;

public interface UserService {
    void confirmCode(String code);
    User getBy(Long userId);
}
