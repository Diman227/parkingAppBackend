package dev.parkingApp.dtos.base;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public abstract class SpotBaseDTO {

    private Long id;

    private String description;
    private String address;

    private BigDecimal rate;
    private BigDecimal price;

    private Long ownerId;

}
