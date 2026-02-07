package dev.parkingApp.controllers;

import dev.parkingApp.dtos.request.BookingRequest;
import dev.parkingApp.dtos.response.BookingResponse;
import dev.parkingApp.mappers.BookingMapper;
import dev.parkingApp.services.BookingService;
import dev.parkingApp.services.kafka.KafkaProducer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "*api/base")
@RequiredArgsConstructor
@Validated
public class BookingController {

    private final BookingService bookingService;
    private final KafkaProducer kafkaProducer;

    private final BookingMapper bookingMapper;

    @GetMapping(value = "/{userId}/bookings")
    @PreAuthorize("#userId == authentication.principal.userId")
    public List<BookingResponse> getUserBookings(@PathVariable("userId") @NotNull @Positive Long userId) {
        return bookingService.getUserBookings(userId);
    }

    @GetMapping(value = "/{userId}/activeBookings")
    @PreAuthorize("#userId == authentication.principal.userId")
    public List<BookingResponse> getUserActiveBookings(@PathVariable("userId") @NotNull @Positive Long userId) {
        return bookingService.getUserActiveBookings(userId);
    }

    @GetMapping(value = "/{userId}/plannedBookings")
    @PreAuthorize("#userId == authentication.principal.userId")
    public List<BookingResponse> getUserPlannedBookings(@PathVariable("userId") @NotNull @Positive Long userId) {
        return bookingService.getUserPlannedBookings(userId);
    }

    @GetMapping(value = "/{userId}/pastBookings")
    @PreAuthorize("#userId == authentication.principal.userId")
    public List<BookingResponse> getUserPastBookings(@PathVariable("userId") @NotNull @Positive Long userId) {
        return bookingService.getUserPastBookings(userId);
    }

    @PostMapping(value = "/bookings")
    @PreAuthorize("#bookingDTO.renterId == authentication.principal.userId")
    public BookingResponse createBooking(@RequestBody @Validated(BookingRequest.Create.class) BookingRequest bookingDTO) {
        kafkaProducer.sendMessageToKafka(bookingDTO);
        return bookingService.createBooking(bookingDTO);
    }

}
