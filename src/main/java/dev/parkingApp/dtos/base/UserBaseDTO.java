package dev.parkingApp.dtos.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class UserBaseDTO {

    private Long id;

    private String surname;
    private String name;
    private String email;

}
