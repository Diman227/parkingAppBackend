package dev.parkingApp.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SignInRequest {

    private String phoneNumber;
    private String password;
    private String surname;
    private String name;
    private String email;
}
