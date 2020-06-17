package ru.itis.services.impl.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.InformationDto;
import ru.itis.models.User;
import ru.itis.repositories.jpa.UserRepositoryJpa;
import ru.itis.services.interfaces.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryJpa userRepository;


    @Override
    @Transactional
    public void confirmCode(String code) {
        userRepository.setUserInfoByCode(code);
    }

    @Override
    public User getBy(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }

    @Override
    public InformationDto getInformation(Long userId) {
        return userRepository.getInformationByUser(userId);
    }
}
