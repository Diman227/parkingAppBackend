package dev.parkingApp.dtos.kafka;

import dev.parkingApp.dtos.base.SpotBaseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SpotMessage extends SpotBaseDTO {

    @NotNull
    @Positive
    @Override
    public Long getId() {
        return super.getId();
    }

    @NotBlank
    @Size(min = 1, max = 256)
    @Override
    public String getDescription(){
        return super.getDescription();
    }

    @NotBlank
    @Size(min = 1, max = 256)
    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @NotNull
    @Positive
    @Override
    public BigDecimal getRate() {
        return super.getRate();
    }

    @NotNull
    @Positive
    @Override
    public BigDecimal getPrice() {
        return super.getPrice();
    }

    @Valid
    private CoordinatesMessage coordinates;

    @NotNull
    @Positive
    @Override
    public Long getOwnerId() {
        return super.getOwnerId();
    }

    private List<ImageMessage> images;
}
