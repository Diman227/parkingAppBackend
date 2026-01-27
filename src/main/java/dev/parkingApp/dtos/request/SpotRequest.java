package dev.parkingApp.dtos.request;

import dev.parkingApp.dtos.response.CoordinatesResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpotRequest {

    @NotNull(groups = BookingRequest.Update.class)
    @Positive(groups = BookingRequest.Update.class)
    private Long id;

    @NotNull(groups = {Create.class, Update.class})
    @NotBlank(groups = {Create.class, Update.class})
    @Size(min = 1, max = 256, groups = {Create.class, Update.class})
    private String description;

    @NotNull(groups = {Create.class, Update.class})
    @NotBlank(groups = {Create.class, Update.class})
    @Size(min = 1, max = 256, groups = {Create.class, Update.class})
    private String address;

    @NotNull(groups = {Create.class, Update.class})
    @Positive(groups = {Create.class, Update.class})
    private BigDecimal rate;

    @NotNull(groups = {Create.class, Update.class})
    @Positive(groups = {Create.class, Update.class})
    private BigDecimal price;

    private LocalDateTime createdAt;

    @Valid
    private CoordinatesResponse location;

    @NotNull(groups = {Create.class, Update.class})
    @Positive(groups = {Create.class, Update.class})
    private Long ownerId;

    private List<ReviewRequest> reviews;

    public interface Create {}
    public interface Update {}

    // todo list с картинками

}
