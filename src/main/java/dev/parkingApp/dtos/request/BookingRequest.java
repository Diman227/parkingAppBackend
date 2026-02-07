package dev.parkingApp.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

    @NotNull(groups = Update.class)
    @Positive(groups = Update.class)
    private Long id;

    @NotNull(groups = {Create.class, Update.class})
    private LocalDateTime startAt;

    @NotNull(groups = {Create.class, Update.class})
    private LocalDateTime endAt;

    @NotNull(groups = {Create.class, Update.class})
    @Positive(groups = {Create.class, Update.class})
    private Long spotId;

    @NotNull(groups = {Create.class, Update.class})
    @Positive(groups = {Create.class, Update.class})
    private Long renterId;

    public interface Create {}
    public interface Update {}
}
