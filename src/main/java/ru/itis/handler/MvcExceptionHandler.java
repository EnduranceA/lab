package ru.itis.handler;

import io.jsonwebtoken.JwtException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice(basePackages = "ru.itis.controllers.mvc")
public class MvcExceptionHandler {

    @ExceptionHandler({AccessDeniedException.class, JwtException.class})
    protected ModelAndView handleAccessDenied(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errMsg", e.getMessage());
        return modelAndView;
    }
}