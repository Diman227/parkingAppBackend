package dev.parkingApp.mappers;

import dev.parkingApp.dtos.request.MessageRequest;
import dev.parkingApp.dtos.response.MessageResponse;
import dev.parkingApp.entities.MessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    // Entity ---> Response

    // todo check mapping
    @Mapping(target = "chatId", source = "chat.id")
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "replyToMessageId", source = "replyTo.id")
    MessageResponse toMessageResponse(MessageEntity message);

    List<MessageResponse> toListMessageResponses(List<MessageRequest> messageRequests);

    // Request ---> Entity

    MessageEntity toMessageEntity(MessageRequest messageRequest);
}
