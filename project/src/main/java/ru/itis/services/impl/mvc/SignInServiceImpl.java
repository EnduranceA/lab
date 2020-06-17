package ru.itis.services.impl.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.SignInDto;
import ru.itis.repositories.jpa.UserRepositoryJpa;
import ru.itis.services.interfaces.SignInService;

@Service(value = "signInServiceMvc")
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UserRepositoryJpa userRepository;

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
