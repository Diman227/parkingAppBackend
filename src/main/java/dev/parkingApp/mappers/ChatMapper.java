package dev.parkingApp.mappers;

import dev.parkingApp.dtos.request.ChatRequest;
import dev.parkingApp.entities.ChatEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MessageMapper.class})
public interface ChatMapper {

    @Mapping(target = "ownerId", source = "owner.id")
    @Mapping(target = "consumerId", source = "consumer.id")
    ChatRequest toChatDTO(ChatEntity chat);

    ChatEntity toChatEntity(ChatRequest chatDTO);

    List<ChatRequest> toListChatDTOs(List<ChatEntity> chats);

    List<ChatEntity> toListChatEntities(List<ChatRequest> chats);
}
