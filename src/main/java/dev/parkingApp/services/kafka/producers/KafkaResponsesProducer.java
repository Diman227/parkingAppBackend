package dev.parkingApp.services.kafka.producers;

import dev.parkingApp.dtos.response.BookingResponse;
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
public class KafkaResponsesProducer {

    @Value("${kafka.topics.responses}")
    private String topicResponsesName;

    private final KafkaTemplate<String, BookingResponse> kafkaTemplate;

    public void sendResponseMessageToKafka(BookingResponse bookingResponse) {

        CompletableFuture<SendResult<String, BookingResponse>> response =  kafkaTemplate.send(topicResponsesName, bookingResponse);
        response.whenComplete((result, ex) -> log.info(String.valueOf(result)));
        log.info("sent message to kafka");
    }
}
