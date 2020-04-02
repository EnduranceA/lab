package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.SignInDto;
import ru.itis.services.SignInService;

@Controller
public class SignInController {

    @Autowired
    public SignInService signInService;

    @GetMapping("/signIn")
    public ModelAndView getSignInPage() {
        return new ModelAndView("sign_in");
    }

    @PostMapping("/signIn")
    public String signIn(@RequestParam("email") String email,
                         @RequestParam("password") String password) {
        SignInDto dto = new SignInDto();
        dto.setEmail(email); dto.setPassword(password);
        signInService.signIn(dto);
        return "redirect:/profile";
    }



}
