package dev.parkingApp.mappers;

import dev.parkingApp.dtos.response.UserResponse;
import dev.parkingApp.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(target = "phoneNumber", source = "credentials.phoneNumber")
//    @Mapping(target = "credentialsId", source = "credentials.id")
    UserResponse toUserDTO(UserEntity userEntity);

    UserEntity toUserEntity(UserResponse userDTO);

}
