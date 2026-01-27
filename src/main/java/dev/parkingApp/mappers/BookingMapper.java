package dev.parkingApp.mappers;

import dev.parkingApp.dtos.request.BookingRequest;
import dev.parkingApp.dtos.response.BookingResponse;
import dev.parkingApp.entities.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { SpotMapper.class, UserMapper.class })
public interface BookingMapper {

    // Entity ---> Response

    BookingResponse toBookingResponse(BookingEntity booking);

    List<BookingResponse> toListBookingResponses(List<BookingEntity> bookings);

    // Request ---> Entity

    BookingEntity toBookingEntity(BookingRequest bookingRequest);

}
