package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.repositories.UserRepository;
import ru.itis.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Override
    @Transactional
    public void confirmCode(String code) {
        userRepository.confirm(code);
    }

}
