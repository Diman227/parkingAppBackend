package dev.parkingApp.dtos.request;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {

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
    private Long spotId;

    @NotNull
    @Positive
    private Long authorId;

    // todo добавить list картинок
}
