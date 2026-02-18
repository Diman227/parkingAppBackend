package dev.parkingApp.services.kafka.consumers;

import dev.parkingApp.dtos.request.SpotRequest;
import dev.parkingApp.services.SpotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.KafkaException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaSpotsConsumer {

    private final SpotService spotService;

    @KafkaListener(id = "parking-app-bookings-spots", topics = "spots", containerFactory = "kafkaListenerSpotsContainerFactory")
    public void getSpotMessage(ConsumerRecord<String, SpotRequest> spotRequest) {

        try {
            log.info("Объект создания места был получен с offset {} - {}", spotRequest.offset(), spotRequest.value());
            spotService.createSpot(spotRequest.value());
            log.info("Объект прочитанный из kafka успешно создан");
        }
        catch (KafkaException ex) {
            log.error("Ошибка при чтении сообщения с  offset {} - {}", spotRequest.offset(), ex.getMessage());
        }
        catch (DataIntegrityViolationException ex) {
            log.error("Нарушение ограничения целостности в бд при чтении сообщения с  offset {} - {}", spotRequest.offset(), ex.getMessage());
        }
    }
}
