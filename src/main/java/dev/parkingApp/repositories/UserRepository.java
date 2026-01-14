package dev.parkingApp.repositories;

import dev.parkingApp.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query(value = "SELECT u FROM UserEntity u WHERE u.credentials.id = :credentialsId")
    UserEntity getUserByCredentials(@Param("credentialsId") Long credentialsId);
}
