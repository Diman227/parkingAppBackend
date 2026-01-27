package dev.parkingApp.dtos.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesResponse {

    private Long  id;

    @NotNull
    @NotBlank
    private String lat;

    @NotNull
    @NotBlank
    private String lon;
}
