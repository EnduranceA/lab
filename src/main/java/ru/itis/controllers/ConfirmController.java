package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.services.ConfirmService;

@Controller
public class ConfirmController {

    @Autowired
    private ConfirmService confirmService;

    @GetMapping("/confirm/{code}")
    public ModelAndView getConfirmation(@PathVariable("code") String confirmCode) {
        confirmService.confirm(confirmCode);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("confirm_page");
        return modelAndView;
    }

}
