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
public class BookingWithSpotDTO {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private BigDecimal totalPrice;

    private SpotWithOwnerDTO spot;

    private Long renterId;


}

