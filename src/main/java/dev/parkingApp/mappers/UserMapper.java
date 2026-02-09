package dev.parkingApp.mappers;

import dev.parkingApp.dtos.request.UserRequest;
import dev.parkingApp.dtos.response.UserResponse;
import dev.parkingApp.entities.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(UserEntity userEntity);

    UserEntity toUserEntity(UserResponse userResponse);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "email", source = "email")
    UserEntity editUserEntity(@MappingTarget UserEntity user, UserRequest userRequest);
}
