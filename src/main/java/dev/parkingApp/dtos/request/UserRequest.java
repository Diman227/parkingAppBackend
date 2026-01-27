package dev.parkingApp.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotNull
    @Positive
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 24)
    private String surname;

    @NotNull
    @NotBlank
    @Size(max = 16)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 32)
    private String email;

}
