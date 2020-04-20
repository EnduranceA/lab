package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.SignUpDto;
import ru.itis.services.SignUpService;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/signUp")
    public ModelAndView getSignUpPage() {
        return new ModelAndView("sign_up");
    }

    @PostMapping("/signUp")
    public String signUp(SignUpDto signUpDto) {
        if (signUpService.signUp(signUpDto)) {
            return "redirect:/signIn";
        }
        return "sign_up";
    }
}
