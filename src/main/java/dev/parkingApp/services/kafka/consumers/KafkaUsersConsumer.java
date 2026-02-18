package dev.parkingApp.services.kafka.consumers;

import dev.parkingApp.dtos.auth.SignInRequest;
import dev.parkingApp.dtos.request.SpotRequest;
import dev.parkingApp.dtos.response.BookingResponse;
import dev.parkingApp.services.AuthService;
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
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaUsersConsumer {

    private final AuthService authService;

    @KafkaListener(id = "parking-app-bookings-users", topics = "users", containerFactory = "kafkaListenerUsersContainerFactory")
    public void listenRegInMessage(@Payload @Valid SignInRequest signInRequest,
                           @Header(KafkaHeaders.OFFSET) Long offset) {

        try {
            log.info("Объект на регистрацию пользователя пришел из kafka с offset - {}: {}", offset, signInRequest.toString());
            authService.createUser(signInRequest);
            log.info("Объект прочитанный из kafka успешно создан");
        }
        catch (KafkaException ex) {
            log.error("Ошибка при чтении сообщения с  offset {} - {}", offset, ex.getMessage());
        }
        catch (DataIntegrityViolationException ex) {
            log.error("Нарушение ограничения целостности в бд при вставке прочитанного объекта с offset {} - {}", offset, ex.getMessage());
        }
    }

}
