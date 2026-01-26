package dev.parkingApp.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {

    private Long id;

    private String message;

    private LocalDateTime sentAt;

    private LocalDateTime readAt;

    private Boolean isRead;

    private Long chatId;

    private Long authorId;

    private ImageResponse attachment;

    private Long replyToMessageId;
}
