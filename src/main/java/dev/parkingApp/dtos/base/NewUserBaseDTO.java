package dev.parkingApp.dtos.base;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class NewUserBaseDTO extends UserBaseDTO {

    private String phoneNumber;
    private String password;
}
