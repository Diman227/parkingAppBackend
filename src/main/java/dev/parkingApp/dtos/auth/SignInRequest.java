package dev.parkingApp.dtos.auth;

import dev.parkingApp.dtos.base.NewUserBaseDTO;
import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@AllArgsConstructor
public class SignInRequest extends NewUserBaseDTO {

    @Null
    @Override
    public Long getId() {
        return super.getId();
    }

    @NotBlank
    @Size(max = 15)
    @Override
    public String getPhoneNumber() {
        return super.getPhoneNumber();
    }

    @NotBlank
    @Size(max = 128)
    public String getPassword() {
        return super.getPassword();
    }

    @NotBlank
    @Size(max = 128)
    @Override
    public String getSurname() {
        return super.getSurname();
    }

    @NotBlank
    @Size(max = 128)
    @Override
    public String getName() {
        return super.getName();
    }

    @NotBlank
    @Size(max = 64)
    @Override
    public String getEmail() {
        return super.getEmail();
    }

}
