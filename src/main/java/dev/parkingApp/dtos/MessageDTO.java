package dev.parkingApp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

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
