package dev.parkingApp.services;

import dev.parkingApp.dtos.request.BookingRequest;
import dev.parkingApp.dtos.response.BookingResponse;
import dev.parkingApp.entities.BookingEntity;
import dev.parkingApp.entities.SpotEntity;
import dev.parkingApp.entities.UserEntity;
import dev.parkingApp.exceptions.SpotBusyException;
import dev.parkingApp.mappers.BookingMapper;
import dev.parkingApp.repositories.BookingRepository;
import dev.parkingApp.repositories.SpotRepository;
import dev.parkingApp.repositories.UserRepository;
import dev.parkingApp.services.kafka.producers.KafkaRequestsProducer;
import dev.parkingApp.services.kafka.producers.KafkaResponsesProducer;
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
    private final UserRepository userRepository;

    private final BookingMapper bookingMapper;

//    private final KafkaRequestsProducer kafkaRequestsProducer;
//    private final KafkaResponsesProducer kafkaResponsesProducer;

    @Transactional
    public BookingResponse createBooking(BookingRequest bookingDTO){

        SpotEntity spot = spotRepository.getReferenceById(bookingDTO.getSpotId());

        if(bookingRepository.isSpotBusyInInterval(
                bookingDTO.getSpotId(),
                bookingDTO.getStartAt(),
                bookingDTO.getEndAt()
        )) {
            throw new SpotBusyException("Unsuccessful attempt to book the spot with id - " + bookingDTO.getSpotId()
                    + " in interval from " + bookingDTO.getStartAt() + " to " + bookingDTO.getEndAt());
        }

        UserEntity renter = userRepository.getReferenceById(bookingDTO.getRenterId());

        BookingEntity booking = bookingMapper.toBookingEntity(bookingDTO);

        booking.setTotalPrice(countTotalPrice(spot.getPrice(), bookingDTO.getStartAt(), bookingDTO.getEndAt()));
        booking.setSpot(spot);
        booking.setRenter(renter);
        booking.setCreatedAt(LocalDateTime.now());

        BookingResponse response = bookingMapper.toBookingResponse(bookingRepository.save(booking));

//        kafkaResponsesProducer.sendResponseMessageToKafka(response);

        return response;
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
