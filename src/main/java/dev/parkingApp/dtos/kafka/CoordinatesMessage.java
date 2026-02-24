package dev.parkingApp.dtos.kafka;

import dev.parkingApp.dtos.base.CoordinatesBaseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CoordinatesMessage extends CoordinatesBaseDTO {

    @Null
    @Override
    public Long getId() {
        return super.getId();
    }

    @NotBlank
    @Override
    public String getLat() {
        return super.getLat();
    }

    @NotBlank
    @Override
    public String getLon() {
        return super.getLon();
    }
}
