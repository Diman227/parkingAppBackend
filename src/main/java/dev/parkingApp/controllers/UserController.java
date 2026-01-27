package dev.parkingApp.controllers;

import dev.parkingApp.dtos.request.UserRequest;
import dev.parkingApp.dtos.response.UserResponse;
import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "*api/base/")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping(value = "user")
    public UserResponse getUser(@AuthenticationPrincipal AuthUser principal) {
        return userService.getUser(principal.getCredentialsId());
    }

    @PutMapping(value = "user")
    @PreAuthorize("#userDTO.id == authentication.principal.userId")
    public UserResponse editUserInfo(@RequestBody @Valid UserRequest userDTO) {
        return userService.editUserInfo(userDTO);
    }
}
