package ru.itis.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.User;
import ru.itis.security.jwt.details.UserDetailsImpl;
import ru.itis.services.interfaces.SongService;
import ru.itis.services.interfaces.UserService;

@Controller
public class ProfileController {

    @Autowired
    public UserService userService;

    @Autowired
    private SongService songService;

    private User user;

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getProfilePage() {
        UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        user = userDetails.getUser();
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("user", user);
        return modelAndView;
    }


    @PostMapping("/profile")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        //проверяем, что файл не пустой
        if (!multipartFile.isEmpty()) {
            songService.save(multipartFile, user);
        }
        return "redirect:/profile";
    }
}
