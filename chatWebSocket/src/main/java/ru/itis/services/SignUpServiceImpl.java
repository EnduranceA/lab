package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.SignUpDto;
import ru.itis.models.Role;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    @Transactional
    public boolean signUp(SignUpDto signUpDto) {
        if (!userRepository.findByEmail(signUpDto.getEmail()).isPresent()){
            User user = User.builder()
                    .email(signUpDto.getEmail())
                    .username(signUpDto.getUsername())
                    .hashPassword(encoder.encode(signUpDto.getPassword()))
                    .role(Role.USER)
                    .build();
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
