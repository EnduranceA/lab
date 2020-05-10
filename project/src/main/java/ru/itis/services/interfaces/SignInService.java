package ru.itis.services.interfaces;

import ru.itis.dto.SignInDto;

public interface SignInService {
    boolean signIn(SignInDto signInDto);
}
