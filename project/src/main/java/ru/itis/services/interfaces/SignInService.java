package ru.itis.services.interfaces;

import ru.itis.dto.SignInDto;
import ru.itis.dto.TokenDto;

public interface SignInService {
    boolean signIn(SignInDto signInDto);
}
