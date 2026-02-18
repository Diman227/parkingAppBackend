package dev.parkingApp.services;

import dev.parkingApp.dtos.request.BookingRequest;
import dev.parkingApp.dtos.response.BookingResponse;
import dev.parkingApp.entities.BookingEntity;
import dev.parkingApp.exceptions.SpotBusyException;
import dev.parkingApp.mappers.BookingMapper;
import dev.parkingApp.repositories.BookingRepository;
import dev.parkingApp.repositories.SpotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SpotRepository spotRepository;

    private final BookingMapper bookingMapper;

    @Transactional
    public BookingResponse createBooking(BookingRequest bookingDTO){

        log.info("Транзакция создания аренды {} начинает работу", Thread.currentThread().getName());

        BigDecimal spotPrice = spotRepository.getSpotPrice(bookingDTO.getSpotId());

        log.info("Получена блокировка на спот {}", bookingDTO.getSpotId());

        //  для проверки
//        try {
//            Thread.sleep(10000); //
//        } catch (InterruptedException e) {}

        if(bookingDTO.getEndAt().isBefore(bookingDTO.getStartAt())) {
            throw new SpotBusyException("Unsuccessful attempt to book the spot with id - " + bookingDTO.getSpotId()
                    + " in interval from " + bookingDTO.getStartAt() + " to " + bookingDTO.getEndAt());
        }
        boolean isBusy = bookingRepository.isSpotBusyInInterval(
                bookingDTO.getSpotId(),
                bookingDTO.getStartAt(),
                bookingDTO.getEndAt()
        );

        log.info("Проверка занятости времени: {}", isBusy);

        if(isBusy){
            throw new SpotBusyException("Spot's busy!");
        }

        BookingEntity booking = bookingMapper.createBookingEntity(bookingDTO);

        booking.setTotalPrice(countTotalPrice(
                spotPrice,
                bookingDTO.getStartAt(),
                bookingDTO.getEndAt()));

        bookingRepository.save(booking);

        return bookingMapper.toBookingResponse(booking);
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
