package dev.parkingApp.services;

import dev.parkingApp.dtos.request.BookingRequest;
import dev.parkingApp.dtos.response.BookingResponse;
import dev.parkingApp.entities.BookingEntity;
import dev.parkingApp.entities.SpotEntity;
import dev.parkingApp.entities.UserEntity;
import dev.parkingApp.mappers.BookingMapper;
import dev.parkingApp.repositories.BookingRepository;
import dev.parkingApp.repositories.SpotRepository;
import dev.parkingApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SpotRepository spotRepository;
    private final UserRepository userRepository;

    private final BookingMapper bookingMapper;

    @Transactional
    public BookingResponse createBooking(BookingRequest bookingDTO){

        SpotEntity spot = spotRepository.getReferenceById(bookingDTO.getSpotId());
        BigDecimal pricePerHour = spot.getPrice();

        UserEntity renter = userRepository.getReferenceById(bookingDTO.getRenterId());

        BookingEntity booking = bookingMapper.toBookingEntity(bookingDTO);

        booking.setTotalPrice(countTotalPrice(pricePerHour, bookingDTO.getStartAt(), bookingDTO.getEndAt()));
        booking.setSpot(spot);
        booking.setRenter(renter);
        booking.setCreatedAt(LocalDateTime.now());

        return bookingMapper.toBookingResponse(bookingRepository.save(booking));
    }

    public List<BookingResponse> getUserBookings(Long userId) {

        return bookingMapper.toListBookingResponses(
                bookingRepository.getUserBookings(userId, LocalDateTime.now())
        );
    }

    public List<BookingResponse> getUserActiveBookings(Long userId) {

        return bookingMapper.toListBookingResponses(
                bookingRepository.getUserActiveBookings(userId, LocalDateTime.now()));
    }

    public List<BookingResponse> getUserPlannedBookings(Long userId) {

        return bookingMapper.toListBookingResponses(
                bookingRepository.getUserPlannedBookings(userId, LocalDateTime.now()));
    }

    public List<BookingResponse> getUserPastBookings(Long userId) {

        return bookingMapper.toListBookingResponses(
                bookingRepository.getUserPastBookings(userId, LocalDateTime.now()));
    }

    private BigDecimal countTotalPrice(BigDecimal pricePerHour, LocalDateTime startAt, LocalDateTime endAt) {
        Duration parkingTime = Duration.between(startAt,endAt);
        return pricePerHour.multiply(new BigDecimal(parkingTime.toHours()));
    }
}
