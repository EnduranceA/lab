package ru.itis.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.services.interfaces.UserService;

@Controller
public class ConfirmController {

    @Autowired
    private UserService confirmService;

    @GetMapping("/confirm/{code}")
    public ModelAndView getConfirmation(@PathVariable("code") String confirmCode) {
        confirmService.confirmCode(confirmCode);
        return new ModelAndView("confirm_page");
    }

}
