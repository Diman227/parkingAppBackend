package dev.parkingApp.dtos.response;

import dev.parkingApp.entities.UserEntity;
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

    private String message;

    private BigDecimal rate;

    private LocalDateTime createdAt;

    private Long reviewedSpotId;

    private String authorFullName;

    private List<ImageResponse> images;

}
