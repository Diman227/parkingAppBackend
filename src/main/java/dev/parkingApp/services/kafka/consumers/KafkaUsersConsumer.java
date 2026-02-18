package dev.parkingApp.services.kafka.consumers;

import dev.parkingApp.dtos.auth.SignInRequest;
import dev.parkingApp.dtos.response.BookingResponse;
import dev.parkingApp.services.AuthService;
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
public class KafkaUsersConsumer {

    private final AuthService authService;

    @KafkaListener(id = "parking-app-bookings-users", topics = "users", containerFactory = "kafkaListenerUsersContainerFactory")
    public void getMessage(ConsumerRecord<String, SignInRequest> signInRequest) {


        try {
            log.info("Объект на регистрацию пользователя пришел из kafka с offset - {}: {}", signInRequest.offset(), signInRequest.value().toString());
            authService.createUser(signInRequest.value());
            log.info("Объект прочитанный из kafka успешно создан");
        }
        catch (KafkaException ex) {
            log.error("Ошибка при чтении сообщения с  offset {} - {}", signInRequest.offset(), ex.getMessage());
        }
        catch (DataIntegrityViolationException ex) {
            log.error("Нарушение ограничения целостности в бд при чтении сообщения с  offset {} - {}", signInRequest.offset(), ex.getMessage());
        }
    }

}
