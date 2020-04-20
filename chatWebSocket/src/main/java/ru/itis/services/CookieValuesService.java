package ru.itis.services;

import ru.itis.models.CookieValue;

import java.util.Optional;

public interface CookieValuesService {
    CookieValue get(String cookie);
    boolean isExist(String cookie);
}
