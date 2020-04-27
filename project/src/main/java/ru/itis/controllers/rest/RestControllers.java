package ru.itis.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.SignInDto;
import ru.itis.dto.SignUpDto;
import ru.itis.dto.TokenDto;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.security.jwt.details.UserDetailsImpl;
import ru.itis.services.interfaces.RestSignInService;
import ru.itis.services.interfaces.SignInService;
import ru.itis.services.interfaces.SignUpService;
import ru.itis.services.interfaces.UserService;

@RestController
public class RestControllers {

    @Autowired
    public RestSignInService signInService;

    @Autowired
    public SignUpService signUpService;

    @Autowired
    public UserService userService;

    @PostMapping("/api/signIn")
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto signInDto) {
        return ResponseEntity.ok(signInService.signIn(signInDto));
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/profile")
    public ResponseEntity<UserDto> getSelf() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        User user = userDetails.getUser();
        return ResponseEntity.ok(UserDto.builder()
                .id(user.getId())
                .email(userDetails.getUsername())
                .role(user.getRole())
                .build());
    }

    @PostMapping("/api/signUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpDto signUpDto) {
        if (signUpService.signUp(signUpDto)) {
            return ResponseEntity.status(HttpStatus.OK).body("You have successfully registered." +
                    "A confirmation email has arrived");
        }
        return ResponseEntity.badRequest().body("A user with this email exists");
    }
}
