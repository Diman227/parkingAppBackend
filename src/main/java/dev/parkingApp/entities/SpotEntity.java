package dev.parkingApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CoordinatesEntity location;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    @OneToMany(mappedBy = "spot",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> images;

    @OneToMany(mappedBy = "spot", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviews;
}
