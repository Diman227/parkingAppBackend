package dev.parkingApp.repositories;

import dev.parkingApp.entities.SpotEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SpotRepository extends JpaRepository<SpotEntity, Long> {

    @Query("""
            SELECT s FROM SpotEntity s
            LEFT JOIN FETCH s.owner
            LEFT JOIN FETCH s.coordinates
            LEFT JOIN FETCH s.images
            WHERE s.owner.id = :userId
            """)
    List<SpotEntity> getUserOwnedSpots(@Param("userId") Long userId);

    @Query("""
            SELECT s FROM SpotEntity s
            LEFT JOIN FETCH s.owner
            LEFT JOIN FETCH s.coordinates
            LEFT JOIN FETCH s.images
            """)
    List<SpotEntity> getAllSpots();


    @Query(value = """
            SELECT s.price FROM SpotEntity s
            WHERE s.id = :spotId
            """)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    BigDecimal getSpotPrice(@Param("spotId") Long spotId);

}
