package dev.parkingApp.controllers;

import dev.parkingApp.dtos.request.UserRequest;
import dev.parkingApp.dtos.response.UserResponse;
import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "*api/base/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> getUser(
            @AuthenticationPrincipal AuthUser principal) {
        return new ResponseEntity<>(userService.getUser(principal.getCredentialsId()), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("#userDTO.id == authentication.principal.userId")
    public ResponseEntity<UserResponse> updateUserInfo(
            @RequestBody @Valid UserRequest userDTO) {
        return new ResponseEntity<>(userService.editUserInfo(userDTO), HttpStatus.OK);
    }
}
