package dev.parkingApp.repositories;

import dev.parkingApp.entities.ImageEntity;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<ImageEntity, Long> {
}
