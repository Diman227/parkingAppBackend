package dev.parkingApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "rents")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_id")
    private Long id;

    private Date createdAt;

    private Date startAt;

    private Date endAt;

    private BigDecimal totalPrice;

    // todo ManyToOne потому что у одного ПМ могут быть брони на разное время
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rented_spot")
    private SpotEntity spot;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "renter_id")
    private UserEntity renter;
}
