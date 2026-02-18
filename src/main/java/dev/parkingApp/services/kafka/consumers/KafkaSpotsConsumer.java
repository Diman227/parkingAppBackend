package dev.parkingApp.services.kafka.consumers;

import dev.parkingApp.dtos.request.SpotRequest;
import dev.parkingApp.services.SpotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.KafkaException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaSpotsConsumer {

    private final SpotService spotService;

    @KafkaListener(id = "parking-app-bookings-spots", topics = "spots", containerFactory = "kafkaListenerSpotsContainerFactory")
    public void listenCreateSpotMessage(@Payload @Validated(SpotRequest.Create.class) SpotRequest spotRequest,
                               @Header(KafkaHeaders.OFFSET) Long offset) {

        try {
            log.info("Объект создания места был получен с offset {} - {}", offset, spotRequest);
            spotService.createSpot(spotRequest);
            log.info("Объект прочитанный из kafka успешно создан");
        }
        catch (KafkaException ex) {
            log.error("Ошибка при чтении сообщения с  offset {} - {}", offset, ex.getMessage());
        }
        catch (DataIntegrityViolationException ex) {
            log.error("Нарушение ограничения целостности в бд при вставке прочитанного объекта с  offset {} - {}", offset, ex.getMessage());
        }
    }
}
