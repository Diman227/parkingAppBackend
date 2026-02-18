package dev.parkingApp.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesRequest {

    private Long  id;

    @NotBlank
    private String lat;

    @NotBlank
    private String lon;
}
