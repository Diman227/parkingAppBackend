package dev.parkingApp.mappers;

import dev.parkingApp.dtos.UserDTO;
import dev.parkingApp.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(target = "phoneNumber", source = "credentials.phoneNumber")
//    @Mapping(target = "credentialsId", source = "credentials.id")
    UserDTO toUserDTO(UserEntity userEntity);

    UserEntity toUserEntity(UserDTO userDTO);

}
