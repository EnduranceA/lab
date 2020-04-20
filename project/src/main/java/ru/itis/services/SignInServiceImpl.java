package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.SignInDto;
import ru.itis.dto.TokenDto;
import ru.itis.helpers.JwtHelper;
import ru.itis.models.State;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;
import ru.itis.services.interfaces.SignInService;
import java.util.Optional;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public TokenDto signIn(SignInDto signInDto) {
        // получаем пользователя по его email
        Optional<User> userOptional = userRepository.findByEmail(signInDto.getEmail());
        // если у меня есть этот пользвователь
        if (userOptional.isPresent()) {
            // получаем его
            User user = userOptional.get();
            //проверям, подтверждена ли регистрация
            if(user.getState().equals(State.CONFIRMED)) {
                // если пароль подходит
                if (passwordEncoder.matches(signInDto.getPassword(), user.getHashPassword())) {
                    // создаем токен
                    String token = jwtHelper.createToken(user);
                    return new TokenDto(token);
                } else throw new AccessDeniedException("Wrong email/password");
            } else throw new AccessDeniedException("Not confirmed code");
        } else throw new AccessDeniedException("User not found");
    }
}
