package dev.parkingApp.repositories;

import dev.parkingApp.entities.ReviewEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends CrudRepository<ReviewEntity, Long> {

    @Query("select r from ReviewEntity r where r.spot.id = :spotId")
    List<ReviewEntity> getSpotReviews(@Param("spotId") Long spotId);

    @Query("select r from ReviewEntity r")
    List<ReviewEntity> getAllReviews();
}
