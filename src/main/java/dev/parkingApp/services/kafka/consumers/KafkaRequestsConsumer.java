package dev.parkingApp.services.kafka.consumers;

import dev.parkingApp.dtos.request.BookingRequest;
import dev.parkingApp.services.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaRequestsConsumer {

    private final BookingService bookingService;

    @KafkaListener(id = "parking-app-bookings-requests", topics = "bookings-requests", containerFactory = "kafkaListenerRequestContainerFactory")
    public void getMessage(ConsumerRecord<String, BookingRequest> bookingRequestMessage) {
        try {
            bookingService.createBooking(bookingRequestMessage.value());
            log.info("Object was received - {}", bookingRequestMessage.value());
        }
        catch (Exception ex) {
            log.error("Error processing offset {}: {}", bookingRequestMessage.offset(), ex.getMessage());
        }
    }
}
