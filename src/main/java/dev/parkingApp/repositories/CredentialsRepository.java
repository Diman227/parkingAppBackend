package dev.parkingApp.repositories;

import dev.parkingApp.entities.CredentialsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CredentialsRepository extends CrudRepository<CredentialsEntity, Long> {

    @Query("""
            SELECT c FROM CredentialsEntity c
            JOIN FETCH c.password
            WHERE c.phoneNumber = :phoneNumber
            """)
    Optional<CredentialsEntity> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

}
