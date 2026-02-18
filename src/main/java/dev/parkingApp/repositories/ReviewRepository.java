package dev.parkingApp.repositories;

import dev.parkingApp.entities.ReviewEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ReviewRepository extends CrudRepository<ReviewEntity, Long> {

    @Query("""
            SELECT r FROM ReviewEntity r
            JOIN FETCH r.author
            LEFT JOIN FETCH r.images i
            WHERE r.spot.id = :spotId
            """)
    List<ReviewEntity> getSpotReviews(@Param("spotId") Long spotId);

    @Query("""
            SELECT COALESCE(AVG(r.rate), 0.0, COUNT(r.id))
            FROM ReviewEntity r
            WHERE r.spot.id = :spotId
            """)
    public BigDecimal calculateSpotRating(@Param("spotId") Long spotId);
}
