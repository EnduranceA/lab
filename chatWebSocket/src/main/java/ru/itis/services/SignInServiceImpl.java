package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.SignInDto;
import ru.itis.models.CookieValue;
import ru.itis.models.User;
import ru.itis.repositories.CookieValuesRepository;
import ru.itis.repositories.UserRepository;
import java.util.Optional;
import java.util.UUID;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CookieValuesRepository cookieValuesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public String  signIn(SignInDto signInDto) {
        String value = null;
        // получаем пользователя по его email
        Optional<User> userOptional = userRepository.findByEmail(signInDto.getEmail());
        // если у меня есть этот пользвователь
        if (userOptional.isPresent()) {
            // получаем его
            User user = userOptional.get();
            // если пароль подходит
            if (passwordEncoder.matches(signInDto.getPassword(), user.getHashPassword())) {
                value = UUID.randomUUID().toString();
                CookieValue cookieValue = CookieValue.builder()
                        .value(value)
                        .user(user)
                        .build();
                cookieValuesRepository.save(cookieValue);
            }
        }
        return value;
    }
}
