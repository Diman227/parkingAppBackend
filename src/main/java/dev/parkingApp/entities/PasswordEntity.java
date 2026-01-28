package dev.parkingApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "passwords")
public class PasswordEntity {

    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public PasswordEntity(String password) {
        this.password = passwordEncoder.encode(password);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "password_id")
    private Long id;

    @Column(nullable = false)
    private String password;

    @JsonIgnore
    private void setPasswordWithEncoding(String password) {
        this.password = passwordEncoder.encode(password);
    }
}
