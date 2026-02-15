package dev.parkingApp.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SignInRequest {

    @NotBlank
    @Size(max = 15)
    private String phoneNumber;

    @NotBlank
    @Size(max = 128)
    private String password;

    @NotBlank
    @Size(max = 128)
    private String surname;

    @NotBlank
    @Size(max = 128)
    private String name;

    @NotBlank
    @Size(max = 64)
    private String email;
}
