package ru.itis.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.security.jwt.details.UserDetailsImpl;

@RestController
public class ProfileRestController {

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
}
