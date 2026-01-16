package dev.parkingApp.services;

import dev.parkingApp.dtos.UserDTO;
import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.entities.UserEntity;
import dev.parkingApp.mappers.UserMapper;
import dev.parkingApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO getUser(Long credentialsId) {

        UserEntity user = userRepository.getUserByCredentials(credentialsId).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return userMapper.toUserDTO(user);
    }

    public UserDTO editUserInfo(UserDTO userDTO) {
        AuthUser principal = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if( userDTO.getId() == principal.getUserId()) {

            UserEntity user = userRepository.findById(userDTO.getId()).orElseThrow();
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setEmail(userDTO.getEmail());
            return userMapper.toUserDTO(userRepository.save(user));
        }
        else {
            throw new RuntimeException("You're not allowed to do this!");
        }

    }
}
