package dev.parkingApp.mappers;

import dev.parkingApp.dtos.BookingDTO;
import dev.parkingApp.dtos.BookingWithSpotDTO;
import dev.parkingApp.entities.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { SpotMapper.class })
public interface BookingMapper {

    @Mapping(target = "spotId", source = "spot.id")
    @Mapping(target = "renterId", source = "renter.id")
    BookingDTO toBookingDTO(BookingEntity booking);

    BookingEntity toBookingEntity(BookingDTO bookingDTO);

    List<BookingDTO> toListBookingDTOs(List<BookingEntity> bookings);

    List<BookingEntity> toListBookingEntities(List<BookingDTO> bookingDTOs);

    BookingWithSpotDTO toBookingWithOwnerDTO(BookingEntity booking);

    List<BookingWithSpotDTO> toListBookingsWithOwnersDTOs(List<BookingEntity> bookingEntities);
}
