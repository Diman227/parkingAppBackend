package dev.parkingApp.controllers;

import dev.parkingApp.dtos.request.BookingRequest;
import dev.parkingApp.dtos.response.BookingResponse;
import dev.parkingApp.mappers.BookingMapper;
import dev.parkingApp.services.BookingService;
import dev.parkingApp.services.kafka.consumers.KafkaResponsesConsumer;
import dev.parkingApp.services.kafka.producers.KafkaRequestsProducer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final KafkaRequestsProducer kafkaRequestProducer;
    private final KafkaResponsesConsumer kafkaResponsesConsumer;

    private final BookingMapper bookingMapper;

    @GetMapping(value = "/{userId}/bookings")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<List<BookingResponse>> getUserBookings(
            @PathVariable("userId") @NotNull @Positive Long userId) {
        return new ResponseEntity<>(bookingService.getUserBookings(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/activeBookings")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<List<BookingResponse>> getUserActiveBookings(
            @PathVariable("userId") @NotNull @Positive Long userId) {
        return new ResponseEntity<>(bookingService.getUserActiveBookings(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/plannedBookings")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<List<BookingResponse>> getUserPlannedBookings(
            @PathVariable("userId") @NotNull @Positive Long userId) {
        return new ResponseEntity<>(bookingService.getUserPlannedBookings(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/pastBookings")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<List<BookingResponse>> getUserPastBookings(
            @PathVariable("userId") @NotNull @Positive Long userId) {
        return new ResponseEntity<>(bookingService.getUserPastBookings(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/bookings")
    @PreAuthorize("#bookingDTO.renterId == authentication.principal.userId")
    public ResponseEntity<BookingResponse> createBooking(
            @RequestBody @Validated(BookingRequest.Create.class) BookingRequest bookingDTO) {
        return new ResponseEntity<>(bookingService.createBooking(bookingDTO), HttpStatus.CREATED);
    }

}
