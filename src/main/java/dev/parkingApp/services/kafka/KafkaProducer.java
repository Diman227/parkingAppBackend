package dev.parkingApp.services.kafka;

import dev.parkingApp.dtos.request.BookingRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, BookingRequest> kafkaTemplate;

    public void sendMessageToKafka(BookingRequest booking) {

        CompletableFuture<SendResult<String, BookingRequest>> response = kafkaTemplate.send(topicName, booking);
        response.whenComplete((result, ex) -> log.info(String.valueOf(result)));
        log.info("sent message to kafka");
    }
}
