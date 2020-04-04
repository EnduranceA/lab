package ru.itis.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ModelAndView getSignUpPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sign_up");
        return modelAndView;
    }

   @PostMapping("/signUp")
    public String signUp(SignUpDto signUpDto) {
        if (signUpService.signUp(signUpDto)) {
            return "redirect:/signIn";
        }
        return "redirect:/signUp";
    }
}
