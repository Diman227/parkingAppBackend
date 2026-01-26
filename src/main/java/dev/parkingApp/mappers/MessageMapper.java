package dev.parkingApp.mappers;

import dev.parkingApp.dtos.request.MessageRequest;
import dev.parkingApp.entities.MessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "chatId", source = "chat.id")
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "attachmentId", source = "attachment.id")
    @Mapping(target = "replyToMessageId", source = "replyTo.id")
    MessageRequest toMessageDTO(MessageEntity message);

    MessageEntity toMessageEntity(MessageRequest messageDTO);
}
