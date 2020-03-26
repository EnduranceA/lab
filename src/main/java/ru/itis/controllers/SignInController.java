package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignInController {

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public ModelAndView getSignInPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sign_in");
        return modelAndView;
    }

}
