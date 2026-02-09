package dev.parkingApp.services;

import dev.parkingApp.dtos.request.UserRequest;
import dev.parkingApp.dtos.response.UserResponse;
import dev.parkingApp.entities.UserEntity;
import dev.parkingApp.exceptions.UserNotFoundException;
import dev.parkingApp.mappers.UserMapper;
import dev.parkingApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse getUser(Long userId) {

        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User with id - " + userId + " - wasn't found!"));

        return userMapper.toUserResponse(user);

    }

    public UserResponse editUserInfo(UserRequest userDTO) {

        UserEntity user = userRepository.findById(userDTO.getId()).orElseThrow(
                () -> new UserNotFoundException("User with id - " + userDTO.getId() + " - wasn't found!"));

        userRepository.save(userMapper.editUserEntity(user, userDTO));

        return userMapper.toUserResponse(user);
    }
}
