package dev.parkingApp.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CoordinatesRequest {

    private Long  id;

    @NotNull
    @NotBlank
    private String lat;

    @NotNull
    @NotBlank
    private String lon;
}
