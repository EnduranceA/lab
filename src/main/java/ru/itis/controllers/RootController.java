package ru.itis.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String getRootPage(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/files";
        }
        return "redirect:/signIn";
    }
}
