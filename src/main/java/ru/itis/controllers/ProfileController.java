package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;
import ru.itis.security.jwt.details.UserDetailsImpl;

import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getProfilePage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        Optional<User> userOptional = userRepository.find(userDetails.getUserId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            ModelAndView modelAndView = new ModelAndView("profile");
            modelAndView.addObject("user", user);
            return modelAndView;
        }
        return null;
    }

}
