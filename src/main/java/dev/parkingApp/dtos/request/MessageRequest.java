package dev.parkingApp.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {

    private Long id;

    private String message;

    private LocalDateTime sentAt;

    private LocalDateTime readAt;

    private Boolean isRead;

    private Long chatId;

    private Long authorId;

    private Long attachmentId;

    private Long replyToMessageId;
}
