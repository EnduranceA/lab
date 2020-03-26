package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.services.ConfirmService;

@Controller
public class ConfirmController {

    @Autowired
    private ConfirmService confirmService;

    @RequestMapping(value = "/confirm/{code}", method = RequestMethod.GET)
    public ModelAndView getConfirmation(@PathVariable("code") String confirmCode) {
        confirmService.confirm(confirmCode);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("confirm_page");
        return modelAndView;
    }

}
