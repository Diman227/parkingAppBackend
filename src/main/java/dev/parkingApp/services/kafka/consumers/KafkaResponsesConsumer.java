package dev.parkingApp.services.kafka.consumers;

import dev.parkingApp.dtos.response.BookingResponse;
import dev.parkingApp.entities.BookingEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaResponsesConsumer {

    @KafkaListener(id = "parking-app-bookings-responses", topics = "bookings-responses", containerFactory = "kafkaListenerResponseContainerFactory")
    public BookingResponse getMessage(ConsumerRecord<String, BookingResponse> bookingResponseMessage) {

        try {
            log.info("Object-BookingResponse was received with value - {}", bookingResponseMessage.value());
        }
        catch (Exception ex) {
            log.error("Error in reading message with offset {} - {}", bookingResponseMessage.offset(), ex.getMessage());
        }
        return bookingResponseMessage.value();
    }

}
