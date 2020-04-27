package ru.itis.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.SignInDto;
import ru.itis.services.interfaces.SignInService;

@Controller
public class SignInController {

    @Autowired
    public SignInService signInService;

    @GetMapping("/signIn")
    public String getSignInPage() {
        return "sign_in";
    }

    @PostMapping("/signIn")
    public String signIn(SignInDto signInDto) {
        System.out.println("I TIT");
        if (signInService.signIn(signInDto)) {
            return "redirect:/profile";
        }
        return "redirect:/signIn?error";
    }
}
