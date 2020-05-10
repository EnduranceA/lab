package ru.itis.services.impl.mvc;

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

@Service(value = "signInServiceMvc")
public class SignInServiceImpl implements SignInService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public boolean signIn(SignInDto signInDto) {
        return true;
//        Optional<User> userOptional = userRepository.findByEmail(signInDto.getEmail());
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            if(user.getState().equals(State.CONFIRMED)) {
//                System.out.println("djknkldm");
//                if (passwordEncoder.matches(signInDto.getPassword(), user.getHashPassword())) {
//                    return true;
//                } else throw new AccessDeniedException("Wrong email/password");
//            } else throw new AccessDeniedException("Not confirmed code");
//        } else throw new AccessDeniedException("User not found");
    }
}
