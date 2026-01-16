package dev.parkingApp.controllers;

import dev.parkingApp.dtos.BookingWithSpotDTO;
import dev.parkingApp.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "*api/base/")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping(value = "{userId}/bookings")
    public List<BookingWithSpotDTO> getUserBookings(@PathVariable("userId") Long userId) {
        //todo principal
        return bookingService.getUserBookings(userId);
    }

    @GetMapping(value = "{userId}/activeBookings")
    public List<BookingWithSpotDTO> getUserActiveBookings(@PathVariable("userId") Long userId) {
        return bookingService.getUserActiveBookings(userId);
    }

    @GetMapping(value = "{userId}/plannedBookings")
    public List<BookingWithSpotDTO> getUserPlannedBookings(@PathVariable("userId") Long userId) {
        return bookingService.getUserPlannedBookings(userId);
    }

    @GetMapping(value = "{userId}/pastBookings")
    public List<BookingWithSpotDTO> getUserPastBookings(@PathVariable("userId") Long userId) {
        return bookingService.getUserPastBookings(userId);
    }

}
