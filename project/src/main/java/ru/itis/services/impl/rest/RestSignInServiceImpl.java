package ru.itis.services.impl.rest;

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
import ru.itis.repositories.jpa.UserRepositoryJpa;
import ru.itis.services.interfaces.RestSignInService;
import java.util.Optional;

@Service(value = "signInServiceRest")
public class RestSignInServiceImpl implements RestSignInService {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserRepositoryJpa userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public TokenDto signIn(SignInDto signInDto) {
        User user = userRepository.findByEmail(signInDto.getEmail());
        if (user != null) {
            if(user.getState().equals(State.CONFIRMED)) {
                if (passwordEncoder.matches(signInDto.getPassword(), user.getHashPassword())) {
                    String token = jwtHelper.createToken(user);
                    return new TokenDto(token);
                } else throw new AccessDeniedException("Wrong email/password");
            } else throw new AccessDeniedException("Not confirmed code");
        } else throw new AccessDeniedException("User not found");
    }

}
