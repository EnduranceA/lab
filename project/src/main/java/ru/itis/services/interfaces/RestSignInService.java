package ru.itis.services.interfaces;

import ru.itis.dto.SignInDto;
import ru.itis.dto.TokenDto;

public interface RestSignInService {
    TokenDto signIn(SignInDto signInDto);
}
