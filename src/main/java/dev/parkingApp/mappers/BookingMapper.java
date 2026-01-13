package dev.parkingApp.mappers;

import dev.parkingApp.dtos.BookingDTO;
import dev.parkingApp.entities.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "spotId", source = "spot.id")
    @Mapping(target = "renterId", source = "renter.id")
    BookingDTO toBookingDTO(BookingEntity booking);

    BookingEntity toBookingEntity(BookingDTO bookingDTO);
}
