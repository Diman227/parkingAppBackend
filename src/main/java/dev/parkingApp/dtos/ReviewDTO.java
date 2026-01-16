package dev.parkingApp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private Long id;

    private String message;

    private BigDecimal rate;

    private LocalDateTime createdAt;

    private Long spotId;

    private Long authorId;

    // todo добавить list картинок
}
