package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.SignInDto;
import ru.itis.dto.TokenDto;
import ru.itis.dto.UserDto;
import ru.itis.models.Role;
import ru.itis.security.jwt.details.UserDetailsImpl;
import ru.itis.services.SignInService;

@RestController
public class RestControllers {

    @Autowired
    public SignInService signInService;

    @PostMapping("/api/signIn")
    public ResponseEntity<TokenDto> login(@RequestBody SignInDto signInDto) {
        return ResponseEntity.ok(signInService.signIn(signInDto));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/profile")
    public ResponseEntity<UserDto> getSelf() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        return ResponseEntity.ok(UserDto.builder()
                .id(userDetails.getUserId())
                .email(userDetails.getUsername())
                .role(Role.valueOf(userDetails.getRole()))
                .build());
    }
}
