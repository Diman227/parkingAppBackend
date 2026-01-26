package dev.parkingApp.mappers;

import dev.parkingApp.dtos.request.BookingRequest;
import dev.parkingApp.dtos.response.BookingResponse;
import dev.parkingApp.entities.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { SpotMapper.class })
public interface BookingMapper {

    @Mapping(target = "spotId", source = "spot.id")
    @Mapping(target = "renterId", source = "renter.id")
    BookingRequest toBookingDTO(BookingEntity booking);

    BookingEntity toBookingEntity(BookingRequest bookingDTO);

    List<BookingRequest> toListBookingDTOs(List<BookingEntity> bookings);

    List<BookingEntity> toListBookingEntities(List<BookingRequest> bookingDTOs);

    BookingResponse toBookingWithOwnerDTO(BookingEntity booking);

    List<BookingResponse> toListBookingsWithOwnersDTOs(List<BookingEntity> bookingEntities);
}
