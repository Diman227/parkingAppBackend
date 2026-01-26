package dev.parkingApp.dtos.response;

import dev.parkingApp.dtos.request.ReviewRequest;
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

    private CoordinatesResponse location;

    private UserResponse owner;

    private List<ReviewRequest> reviews;

    // todo list с картинками

}
