package dev.parkingApp.services;

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

    public UserResponse getUser(Long credentialsId) {

        UserEntity user = userRepository.getUserByCredentials(credentialsId).orElseThrow(
                () -> new UserNotFoundException("User with credentialsId - " + credentialsId + " - wasn't found!"));
        return userMapper.toUserResponse(user);

    }

    public UserResponse editUserInfo(UserResponse userDTO) {

        UserEntity user = userRepository.findById(userDTO.getId()).orElseThrow(
                () -> new UserNotFoundException("User with id - " + userDTO.getId() + " - wasn't found!")
        );
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());

        return userMapper.toUserResponse(userRepository.save(user));
    }
}
