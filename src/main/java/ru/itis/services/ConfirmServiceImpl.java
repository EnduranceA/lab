package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.repositories.UserRepository;

@Service
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void confirm(String code) {
        userRepository.confirm(code);
    }
}
