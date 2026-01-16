package dev.parkingApp.services;

import dev.parkingApp.dtos.BookingDTO;
import dev.parkingApp.dtos.BookingWithSpotDTO;
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

//    @Transactional
    public BookingDTO createBooking(BookingDTO bookingDTO){
        //todo с аннотацией он выполняет запросы и получает эти объекты в прокси, без аннотации нет запросов, но выбрасываются исключения, но в итоге все отрабатывает правильно
        SpotEntity spot = spotRepository.getReferenceById(bookingDTO.getSpotId());
        UserEntity renter = userRepository.getReferenceById(bookingDTO.getRenterId());

        BookingEntity booking = bookingMapper.toBookingEntity(bookingDTO);

        booking.setSpot(spot);
        booking.setRenter(renter);

        return bookingMapper.toBookingDTO(bookingRepository.save(booking));
    }

    public List<BookingWithSpotDTO> getUserBookings(Long userId) {
        return bookingMapper.toListBookingsWithOwnersDTOs(
                bookingRepository.getUserBookings(userId, LocalDateTime.now())
        );
    }

    // todo как время должно передаваться
    public List<BookingWithSpotDTO> getUserActiveBookings(Long userId) {
        LocalDateTime currentDate = LocalDateTime.now();
        return bookingMapper.toListBookingsWithOwnersDTOs(bookingRepository.getUserActiveBookings(userId, currentDate));
    }

    public List<BookingWithSpotDTO> getUserPlannedBookings(Long userId) {
        LocalDateTime currentDate = LocalDateTime.now();
        return bookingMapper.toListBookingsWithOwnersDTOs(bookingRepository.getUserPlannedBookings(userId, currentDate));
    }

    public List<BookingWithSpotDTO> getUserPastBookings(Long userId) {
        LocalDateTime currentDate = LocalDateTime.now();
        return bookingMapper.toListBookingsWithOwnersDTOs(bookingRepository.getUserPastBookings(userId, currentDate));
    }
}
