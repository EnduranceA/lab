package ru.itis.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.SignUpDto;
import ru.itis.services.interfaces.SignUpService;
import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/signUp")
    @PreAuthorize("permitAll()")
    public String getSignUpPage(Model model) {
        model.addAttribute("signUpDto", new SignUpDto());
        return "sign_up";
    }

   @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute("signUpDto")SignUpDto signUpDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            if (signUpService.signUp(signUpDto)) {
                return "redirect:/signIn";
            }
        }
        model.addAttribute("signUpDto", signUpDto);
        return "sign_up";
   }
}
