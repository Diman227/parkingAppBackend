package dev.parkingApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "spots")
public class SpotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id")
    private Long externalId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String address;

    @Transient
    private BigDecimal rate;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "coordinates_id")
    private CoordinatesEntity coordinates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private UserEntity owner;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "external_owner_id")
    private Long externalOwnerId;

    @OneToMany(mappedBy = "spot",fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ImageEntity> images = new ArrayList<>();

    @OneToMany(mappedBy = "spot", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ReviewEntity> reviews = new ArrayList<>();

    // todo calculate in db
    @PostLoad
    public void setSpotRate(){
        this.rate = new BigDecimal(5);
    }

}
