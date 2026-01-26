package dev.parkingApp.mappers;

import dev.parkingApp.dtos.response.UserResponse;
import dev.parkingApp.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(UserEntity userEntity);

    UserEntity toUserEntity(UserResponse userResponse);
}
