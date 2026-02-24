package dev.parkingApp.dtos.response;

import dev.parkingApp.dtos.base.SpotBaseDTO;
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
public class SpotResponse extends SpotBaseDTO {

    private LocalDateTime createdAt;

    private CoordinatesResponse coordinates;

    private UserResponse owner;

    private List<ImageResponse> images;

}
