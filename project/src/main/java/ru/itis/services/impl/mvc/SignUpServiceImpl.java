package ru.itis.services.impl.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.SignUpDto;
import ru.itis.models.Role;
import ru.itis.models.State;
import ru.itis.models.User;
import ru.itis.repositories.jpa.UserRepositoryJpa;
import ru.itis.services.interfaces.EmailService;
import ru.itis.services.interfaces.SignUpService;
import java.time.LocalDateTime;
import java.util.UUID;

@Component(value = "signUpService")
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepositoryJpa userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EmailService emailService;

    @Override
    @Transactional
    public boolean signUp(SignUpDto signUpDto) {
        User users = userRepository.findByEmail(signUpDto.getEmail());
        if (users == null){
            User user = User.builder()
                    .email(signUpDto.getEmail())
                    .firstName(signUpDto.getFirstName())
                    .lastName(signUpDto.getLastName())
                    .hashPassword(encoder.encode(signUpDto.getPassword()))
                    .role(Role.valueOf(signUpDto.getRole()))
                    .state(State.NOT_CONFIRMED)
                    .confirmCode(UUID.randomUUID().toString())
                    .createdAt(LocalDateTime.now())
                    .build();
            userRepository.save(user);
            emailService.sendConfirmMessage(user.getConfirmCode(), user.getEmail());
            return true;
        }
        return false;
    }
}
