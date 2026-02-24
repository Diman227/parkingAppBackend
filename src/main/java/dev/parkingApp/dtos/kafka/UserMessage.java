package dev.parkingApp.dtos.kafka;

import dev.parkingApp.dtos.base.NewUserBaseDTO;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserMessage extends NewUserBaseDTO {

    @NotNull
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

    @NotNull
    @NotBlank
    @Size(max = 24)
    @Override
    public String getSurname() {
        return super.getSurname();
    }

    @NotNull
    @NotBlank
    @Size(max = 16)
    @Override
    public String getName() {
        return super.getName();
    }

    @NotNull
    @NotBlank
    @Size(max = 32)
    @Override
    public String getEmail() {
        return super.getEmail();
    }
}
