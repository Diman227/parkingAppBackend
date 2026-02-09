package dev.parkingApp.mappers;

import dev.parkingApp.dtos.request.BookingRequest;
import dev.parkingApp.dtos.response.BookingResponse;
import dev.parkingApp.entities.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = { SpotMapper.class, UserMapper.class },
        imports = { LocalDateTime.class })

public interface BookingMapper {

    // Entity ---> Response

    BookingResponse toBookingResponse(BookingEntity booking);

    List<BookingResponse> toListBookingResponses(List<BookingEntity> bookings);

    // Request ---> Entity

    BookingEntity toBookingEntity(BookingRequest bookingRequest);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "spot.id", source = "spotId")
    @Mapping(target = "renter.id", source = "renterId")
    BookingEntity createBookingEntity(BookingRequest bookingRequest);

}
