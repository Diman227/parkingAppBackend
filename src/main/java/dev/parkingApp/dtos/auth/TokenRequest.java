package dev.parkingApp.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenRequest {

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String phoneNumber;

    @NotNull
    @NotBlank
    @Size(max = 128)
    private String password;
}
