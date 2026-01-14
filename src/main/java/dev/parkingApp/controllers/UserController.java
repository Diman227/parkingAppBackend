package dev.parkingApp.controllers;

import dev.parkingApp.dtos.UserDTO;
import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.entities.UserEntity;
import dev.parkingApp.mappers.UserMapper;
import dev.parkingApp.repositories.UserRepository;
import dev.parkingApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "*api/base/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping(value = "user")
    public UserDTO getUser(@AuthenticationPrincipal AuthUser principal) {

        return this.userService.getUser(principal.getCredentialsId());
    }

    @PutMapping(value = "user")
    public UserDTO editUserInfo(@RequestBody UserDTO userDTO) {
        return this.userService.editUserInfo(userDTO);
    }
}
