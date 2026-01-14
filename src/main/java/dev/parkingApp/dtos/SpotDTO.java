package dev.parkingApp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpotDTO {

    private Long id;

    private String description;

    private String address;

    private BigDecimal rate;

    private BigDecimal price;

    private Date createdAt;

    private CoordinatesDTO location;

    private Long ownerId;

    private List<ReviewDTO> reviews;

    // todo list с картинками

}
