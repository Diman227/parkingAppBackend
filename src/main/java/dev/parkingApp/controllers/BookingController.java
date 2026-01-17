package dev.parkingApp.controllers;

import dev.parkingApp.dtos.BookingDTO;
import dev.parkingApp.dtos.BookingWithSpotDTO;
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
    public List<BookingWithSpotDTO> getUserBookings(@PathVariable("userId") Long userId) {
        return bookingService.getUserBookings(userId);
    }

    @GetMapping(value = "{userId}/activeBookings")
    @PreAuthorize("#userId == authentication.principal.userId")
    public List<BookingWithSpotDTO> getUserActiveBookings(@PathVariable("userId") Long userId) {
        return bookingService.getUserActiveBookings(userId);
    }

    @GetMapping(value = "{userId}/plannedBookings")
    @PreAuthorize("#userId == authentication.principal.userId")
    public List<BookingWithSpotDTO> getUserPlannedBookings(@PathVariable("userId") Long userId) {
        return bookingService.getUserPlannedBookings(userId);
    }

    @GetMapping(value = "{userId}/pastBookings")
    @PreAuthorize("#userId == authentication.principal.userId")
    public List<BookingWithSpotDTO> getUserPastBookings(@PathVariable("userId") Long userId) {
        return bookingService.getUserPastBookings(userId);
    }

    @PostMapping(value = "bookings")
    @PreAuthorize("#booking.renterId == authentication.principal.userId")
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }

}
