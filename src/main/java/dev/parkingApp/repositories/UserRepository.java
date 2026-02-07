package dev.parkingApp.repositories;

import dev.parkingApp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.credentials.id = :credentialsId")
    Optional <UserEntity> getUserByCredentials(@Param("credentialsId") Long credentialsId);

    @Query("""
            SELECT u FROM UserEntity u
            JOIN FETCH u.credentials c
            JOIN FETCH c.password
            WHERE u.credentials.phoneNumber = :username
            """)
    Optional <UserEntity> getUserByUsername(@Param("username") String username);
}
