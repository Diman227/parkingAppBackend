package dev.parkingApp.dtos.request;

import dev.parkingApp.dtos.response.CoordinatesResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpotRequest {

    @Null(groups = BookingRequest.Create.class)
    @NotNull(groups = BookingRequest.Update.class)
    @Positive(groups = BookingRequest.Update.class)
    private Long id;

    @NotBlank(groups = {Create.class, Update.class})
    @Size(min = 1, max = 256, groups = {Create.class, Update.class})
    private String description;

    @NotBlank(groups = {Create.class, Update.class})
    @Size(min = 1, max = 256, groups = {Create.class, Update.class})
    private String address;

    @NotNull(groups = {Create.class, Update.class})
    @Positive(groups = {Create.class, Update.class})
    private BigDecimal rate;

    @NotNull(groups = {Create.class, Update.class})
    @Positive(groups = {Create.class, Update.class})
    private BigDecimal price;

    @Valid
    private CoordinatesRequest location;

    @NotNull(groups = {Create.class, Update.class})
    @Positive(groups = {Create.class, Update.class})
    private Long ownerId;

    private List<ImageRequest> images;

    public interface Create {}
    public interface Update {}

}
