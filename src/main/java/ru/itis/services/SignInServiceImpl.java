package ru.itis.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dto.SignInDto;
import ru.itis.dto.TokenDto;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.util.Optional;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public TokenDto signIn(SignInDto signInDto) {
        // получаем пользователя по его email
        Optional<User> userOptional = userRepository.findByEmail(signInDto.getEmail());
        // если у меня есть этот пользвователь
        if (userOptional.isPresent()) {
            // получаем его
            User user = userOptional.get();
            //проверям, подтверждена ли регистрация
            if(userRepository.isConfirmed(user.getConfirmCode())) {
                // если пароль подходит
                if (passwordEncoder.matches(signInDto.getPassword(), user.getHashPassword())) {
                    // создаем токен
                    String token = Jwts.builder()
                            .setSubject(user.getId().toString()) // id пользователя
                            .claim("email", user.getEmail()) // имя
                            .claim("role", user.getRole().name()) // роль
                            .signWith(SignatureAlgorithm.HS256, secret) // подписываем его с нашим secret
                            .compact(); // преобразовали в строку
                    System.out.println(token);
                    return new TokenDto(token);
                } else throw new AccessDeniedException("Not confirmed code");
            } else throw new AccessDeniedException("Wrong email/password");
        } else throw new AccessDeniedException("User not found");
    }

}
