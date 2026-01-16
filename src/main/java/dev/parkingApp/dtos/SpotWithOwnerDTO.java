package dev.parkingApp.dtos;

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
public class SpotWithOwnerDTO {

    private Long id;

    private String description;

    private String address;

    private BigDecimal rate;

    private BigDecimal price;

    private LocalDateTime createdAt;

    private CoordinatesDTO location;

    private UserDTO owner;

    private List<ReviewDTO> reviews;

    // todo list с картинками

}
