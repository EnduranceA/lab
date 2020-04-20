package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.CookieValue;
import ru.itis.repositories.CookieValuesRepository;

import java.util.Optional;

@Service
public class CookieValuesServiceImpl implements CookieValuesService {

    @Autowired
    private CookieValuesRepository cookieValuesRepository;

    @Override
    public CookieValue get(String cookie) {
        Optional<CookieValue> cookieValueOptional = cookieValuesRepository.findByValue(cookie);
        return cookieValueOptional.orElse(null);
    }

    @Override
    public boolean isExist(String cookie) {
        Optional<CookieValue> cookieValueOptional = cookieValuesRepository.findByValue(cookie);
        return cookieValueOptional.isPresent();
    }
}
