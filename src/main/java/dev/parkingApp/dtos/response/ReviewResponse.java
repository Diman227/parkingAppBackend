package dev.parkingApp.dtos.response;

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
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

    private Long id;

    @Length(max = 256, message = "Максимальный объем отзыва - 256 символов")
    private String message;

    @NotNull
    @Min(1)
    @Max(5)
    private BigDecimal rate;

    private LocalDateTime createdAt;

    @NotNull
    @Positive
    private Long reviewedSpotId;

    @NotNull
    private UserResponse author;

    private List<ImageResponse> images;
}
