package ru.itis.services;

import ru.itis.dto.SignInDto;
import ru.itis.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInDto);
}
