package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.SignUpDto;
import ru.itis.services.SignUpService;

@RestController
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
    public ModelAndView signUp(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("email") String email,
                               @RequestParam("password")String password) {
       SignUpDto signUpDto = SignUpDto.builder()
               .email(email)
               .firstName(firstName)
               .lastName(lastName)
               .password(password)
               .build();
        signUpService.signUp(signUpDto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sign_up");
        return modelAndView;
    }
}
