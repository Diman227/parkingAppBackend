package dev.parkingApp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private Long id;

    private Date createdAt;

    private Date startAt;

    private Date endAt;

    private BigDecimal totalPrice;

    private Long spotId;

    private Long renterId;


}
