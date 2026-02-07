package dev.parkingApp.dtos.request;

import dev.parkingApp.dtos.response.ImageResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {

    @NotNull(groups = Update.class)
    @Positive(groups = Update.class)
    private Long id;

    @Length(max = 256, groups = {Create.class, Update.class}, message = "Максимальный объем отзыва - 256 символов")
    private String message;

    @NotNull(groups = {Create.class, Update.class})
    @Min(value = 1, groups = {Create.class, Update.class})
    @Max(value = 5, groups = {Create.class, Update.class})
    private BigDecimal rate;

    @NotNull(groups = {Create.class, Update.class})
    @Positive(groups = {Create.class, Update.class})
    private Long spotId;

    @NotNull(groups = {Create.class, Update.class})
    @Positive(groups = {Create.class, Update.class})
    private Long authorId;

    private List<ImageRequest> images;

    public interface Create {};
    public interface Update {};
}
