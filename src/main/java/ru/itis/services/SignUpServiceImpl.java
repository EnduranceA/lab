package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.models.Role;
import ru.itis.models.State;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;
import java.util.UUID;

@Component(value = "signUpService")
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EmailService emailService;

    @Override
    public void signUp(String username, String email, String password) {
        User user = User.builder()
                .email(email)
                .username(username)
                .hashPassword(encoder.encode(password))
                .role(Role.USER)
                .state(State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .build();
        userRepository.save(user);

        emailService.sendConfirmMessage(user.getConfirmCode(), user.getEmail());
    }
}
