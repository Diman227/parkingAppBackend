package dev.parkingApp.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequest {

    private Long id;

    private Long ownerId;

    private Long consumerId;

    private List<MessageRequest> messages;
}
