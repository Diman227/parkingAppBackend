package dev.parkingApp.dtos.response;

import dev.parkingApp.dtos.request.MessageRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {

    private Long id;

    private UserResponse owner;

    private UserResponse consumer;

    private List<MessageRequest> messages;
}
