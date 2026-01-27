package dev.parkingApp.controllers;

import dev.parkingApp.dtos.request.BookingRequest;
import dev.parkingApp.dtos.response.BookingResponse;
import dev.parkingApp.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "*api/base/")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping(value = "{userId}/bookings")
    @PreAuthorize("#userId == authentication.principal.userId")
    public List<BookingResponse> getUserBookings(@PathVariable("userId") Long userId) {
        return bookingService.getUserBookings(userId);
    }

    @GetMapping(value = "{userId}/activeBookings")
    @PreAuthorize("#userId == authentication.principal.userId")
    public List<BookingResponse> getUserActiveBookings(@PathVariable("userId") Long userId) {
        return bookingService.getUserActiveBookings(userId);
    }

    @GetMapping(value = "{userId}/plannedBookings")
    @PreAuthorize("#userId == authentication.principal.userId")
    public List<BookingResponse> getUserPlannedBookings(@PathVariable("userId") Long userId) {
        return bookingService.getUserPlannedBookings(userId);
    }

    @GetMapping(value = "{userId}/pastBookings")
    @PreAuthorize("#userId == authentication.principal.userId")
    public List<BookingResponse> getUserPastBookings(@PathVariable("userId") Long userId) {
        return bookingService.getUserPastBookings(userId);
    }

    @PostMapping(value = "bookings")
    @PreAuthorize("#booking.renterId == authentication.principal.userId")
    public BookingResponse createBooking(BookingRequest bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }

}
