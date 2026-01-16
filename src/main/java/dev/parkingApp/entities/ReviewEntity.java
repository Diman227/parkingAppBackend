package dev.parkingApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String message;

    private BigDecimal rate;

    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id")
    private SpotEntity spot;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private UserEntity author;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> images;
}
