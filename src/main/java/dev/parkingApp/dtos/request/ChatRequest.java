package dev.parkingApp.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequest {

    private Long id;

    @NotNull
    @Positive
    private Long ownerId;

    @NotNull
    @Positive
    private Long consumerId;
}
