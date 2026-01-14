package dev.parkingApp.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class AuthUser implements UserDetails {

     //todo сюда id
     private String password;
     private String username;
     private String phoneNumber;
     private boolean enabled;
     private List<SimpleGrantedAuthority> authorities;
     private Long credentialsId;
}
