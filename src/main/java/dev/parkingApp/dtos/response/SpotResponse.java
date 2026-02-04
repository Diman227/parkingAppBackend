package dev.parkingApp.dtos.response;

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
public class SpotResponse {

    private Long id;

    private String description;

    private String address;

    private BigDecimal rate;

    private BigDecimal price;

    private LocalDateTime createdAt;

    private CoordinatesResponse coordinates;

    private UserResponse owner;

    private List<ImageResponse> images;

}
