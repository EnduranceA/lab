package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.services.SignUpService;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public ModelAndView getSignUpPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sign_up");
        return modelAndView;
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ModelAndView signUp(@RequestParam("username") String username, @RequestParam("email")
                                   String email, @RequestParam("password")String password) {
        signUpService.signUp(username, email, password);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sign_up");
        return modelAndView;
    }
}
