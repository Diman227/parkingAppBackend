package dev.parkingApp.dtos.request;

import dev.parkingApp.dtos.base.SpotBaseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpotRequest extends SpotBaseDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    @Positive(groups = Update.class)
    @Override
    public Long getId() {
        return super.getId();
    }

    @NotBlank(groups = {Create.class, Update.class})
    @Size(min = 1, max = 256, groups = {Create.class, Update.class})
    @Override
    public String getDescription(){
        return super.getDescription();
    }

    @NotBlank(groups = {Create.class, Update.class})
    @Size(min = 1, max = 256, groups = {Create.class, Update.class})
    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @NotNull(groups = {Create.class, Update.class})
    @Positive(groups = {Create.class, Update.class})
    @Override
    public BigDecimal getRate() {
        return super.getRate();
    }

    @NotNull(groups = {Create.class, Update.class})
    @Positive(groups = {Create.class, Update.class})
    @Override
    public BigDecimal getPrice() {
        return super.getPrice();
    }

    @Valid
    private CoordinatesRequest coordinates;

    @NotNull(groups = {Create.class, Update.class})
    @Positive(groups = {Create.class, Update.class})
    @Override
    public Long getOwnerId() {
        return super.getOwnerId();
    }

    private List<ImageRequest> images;

}
