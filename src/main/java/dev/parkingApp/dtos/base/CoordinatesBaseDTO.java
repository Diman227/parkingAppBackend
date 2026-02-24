package dev.parkingApp.dtos.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CoordinatesBaseDTO {

    private Long  id;

    private String lat;
    private String lon;
}
