package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.dto.SignUpDto;
import ru.itis.models.Role;
import ru.itis.models.State;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public void signUp(SignUpDto signUpDto) {
        User user = User.builder()
                .email(signUpDto.getEmail())
                .firstName(signUpDto.getFirstName())
                .lastName(signUpDto.getLastName())
                .hashPassword(encoder.encode(signUpDto.getPassword()))
                .role(Role.USER)
                .state(State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(user);

        emailService.sendConfirmMessage(user.getConfirmCode(), user.getEmail());
    }
}
