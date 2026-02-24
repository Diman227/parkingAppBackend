package dev.parkingApp.mappers;

import dev.parkingApp.dtos.base.NewUserBaseDTO;
import dev.parkingApp.dtos.kafka.UserMessage;
import dev.parkingApp.dtos.request.UserRequest;
import dev.parkingApp.dtos.response.UserResponse;
import dev.parkingApp.entities.CredentialsEntity;
import dev.parkingApp.entities.PasswordEntity;
import dev.parkingApp.entities.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Entity ---> Response

    UserResponse toUserResponse(UserEntity userEntity);

    // Request ---> Entity

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "email", source = "email")
    UserEntity editUserEntity(@MappingTarget UserEntity user, UserRequest userRequest);

    // BaseDTO ---> Entity

    default UserEntity createUserFromBaseDTO(NewUserBaseDTO userBaseDTO) {

        final CredentialsEntity credentials = CredentialsEntity.builder()
                .phoneNumber(userBaseDTO.getPhoneNumber())
                .password(new PasswordEntity(userBaseDTO.getPassword()))
                .build();

        return UserEntity.builder()
                .surname(userBaseDTO.getSurname())
                .name(userBaseDTO.getName())
                .email(userBaseDTO.getEmail())
                .credentials(credentials)
                .build();
    }
}
