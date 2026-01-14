package dev.parkingApp.services;

import dev.parkingApp.dtos.UserDTO;
import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.entities.UserEntity;
import dev.parkingApp.mappers.UserMapper;
import dev.parkingApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

//    UserDTO addUser(UserDTO userDTO) {
//        UserEntity user = userMapper.toUserEntity(userDTO);
//        return userMapper.toUserDTO(userRepository.save(user));
//    }

    public UserDTO getUser(Long credentialsId) {

        UserEntity user = userRepository.getUserByCredentials(credentialsId);
        return userMapper.toUserDTO(user);
    }

    public UserDTO editUserInfo(UserDTO userDTO) {
        UserEntity user = userMapper.toUserEntity(userDTO);
        return userMapper.toUserDTO(userRepository.save(user));
    }
}
