package dev.parkingApp.repositories;


import dev.parkingApp.entities.ChatEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends CrudRepository<ChatEntity, Long> {

    @Query("select c from ChatEntity c where " +
            "c.owner.id = :userId OR c.consumer.id = :userId")
    List<ChatEntity> getUserChats(@Param("userId") Long userId);
}
