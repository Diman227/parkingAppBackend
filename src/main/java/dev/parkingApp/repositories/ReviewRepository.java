package dev.parkingApp.repositories;

import dev.parkingApp.entities.ReviewEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<ReviewEntity, Long> {
}
