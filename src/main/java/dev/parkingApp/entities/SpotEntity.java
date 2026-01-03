package dev.parkingApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "spots")
public class SpotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spot_id")
    private Long id;

    private String description;

    private String address;

    private BigDecimal rate;

    private BigDecimal price;

    private Date createdAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CoordinatesEntity location;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserEntity owner;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> images;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviews;
}
