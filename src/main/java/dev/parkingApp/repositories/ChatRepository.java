package dev.parkingApp.repositories;


import dev.parkingApp.entities.ChatEntity;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<ChatEntity, Long> {

    // todo getUserChats
}
