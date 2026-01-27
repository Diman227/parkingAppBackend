package dev.parkingApp.mappers;

import dev.parkingApp.dtos.request.ChatRequest;
import dev.parkingApp.dtos.response.ChatResponse;
import dev.parkingApp.entities.ChatEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, MessageMapper.class})
public interface ChatMapper {

    // Entity ---> Response

    ChatResponse toChatResponse(ChatEntity chat);

    List<ChatResponse> toListChatResponses(List<ChatEntity> chats);

    // Request ---> Entity

    ChatEntity toChatEntity(ChatRequest chatRequest);

}
