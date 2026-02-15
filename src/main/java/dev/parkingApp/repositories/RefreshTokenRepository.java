package dev.parkingApp.repositories;

import dev.parkingApp.entities.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    @Query("""
            SELECT t FROM RefreshTokenEntity t
            WHERE t.credentialsId = :credentialsId
            """)
    Optional<RefreshTokenEntity> findTokenByCredentialsId(@Param("credentialsId") Long credentialsId);


}
