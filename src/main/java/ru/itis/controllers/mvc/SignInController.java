package ru.itis.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.SignInDto;
import ru.itis.dto.TokenDto;
import ru.itis.services.interfaces.SignInService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignInController {

    @Autowired
    public SignInService signInService;

    @GetMapping("/signIn")
    public ModelAndView getSignInPage() {
        return new ModelAndView("sign_in");
    }

    @PostMapping("/signIn")
    public String signIn(SignInDto signInDto, HttpServletResponse servletResponse) {
        TokenDto tokenDto = signInService.signIn(signInDto);
        Cookie tokenCookie = new Cookie("Authorization", tokenDto.getToken());
        servletResponse.addCookie(tokenCookie);
        return "redirect:/profile";
    }
}
