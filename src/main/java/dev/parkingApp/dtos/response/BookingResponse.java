package dev.parkingApp.dtos.response;

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
public class BookingResponse {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private BigDecimal totalPrice;

    private SpotResponse spot;

    private Long renterId;


}

