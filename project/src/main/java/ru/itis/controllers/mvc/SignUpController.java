package ru.itis.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.SignUpDto;
import ru.itis.services.interfaces.SignUpService;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/signUp")
    @PreAuthorize("permitAll()")
    public String getSignUpPage() {
        return "sign_up";
    }

   @PostMapping("/signUp")
    public String signUp(SignUpDto signUpDto) {
        if (signUpService.signUp(signUpDto)) {
            return "redirect:/signIn";
        }
        return "redirect:/signUp";
    }
}
