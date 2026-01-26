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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
        //todo с аннотацией он выполняет запросы и получает эти данные в прокси, без аннотации нет запросов, но выбрасываются исключения, но в итоге все отрабатывает правильно
        // по сути, как я понял, из-за отладки происходит запрос в бд, тк интерфейс отладки хочет отобразить объект и это считается обращением к его полям
        SpotEntity spot = spotRepository.getReferenceById(bookingDTO.getSpotId());

        UserEntity renter = userRepository.getReferenceById(bookingDTO.getRenterId());

        BookingEntity booking = bookingMapper.toBookingEntity(bookingDTO);

        booking.setSpot(spot);
        booking.setRenter(renter);

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
}
