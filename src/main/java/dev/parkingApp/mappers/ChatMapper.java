package dev.parkingApp.mappers;

import dev.parkingApp.dtos.ChatDTO;
import dev.parkingApp.entities.ChatEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MessageMapper.class})
public interface ChatMapper {

    @Mapping(target = "ownerId", source = "owner.id")
    @Mapping(target = "consumerId", source = "consumer.id")
    ChatDTO toChatDTO(ChatEntity chat);

    ChatEntity toChatEntity(ChatDTO chatDTO);
}
