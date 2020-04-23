package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.dto.SignInDto;
import ru.itis.services.SignInService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignInController {

    @Autowired
    public SignInService signInService;

    @GetMapping("/signIn")
    public String getSignInPage() {
        return "sign_in";
    }

    @PostMapping("/signIn")
    public String signIn(SignInDto signInDto, HttpServletResponse response) {
        String cookieValue = signInService.signIn(signInDto);
        if (cookieValue == null) {
            return "redirect:/signIn?error";
        }
        Cookie cookie = new Cookie("AuthCookie", cookieValue);
        response.addCookie(cookie);
        return "redirect:/index";
    }

}
