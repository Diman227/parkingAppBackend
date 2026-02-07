package dev.parkingApp.services.kafka;

import dev.parkingApp.dtos.request.BookingRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(id = "parking-app-bookings", topics = "bookings")
    public void getMessage(BookingRequest bookingRequest) {
    }
}
