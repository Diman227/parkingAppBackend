package dev.parkingApp.repositories;

import dev.parkingApp.entities.SpotEntity;
import org.springframework.data.repository.CrudRepository;

public interface SpotRepository extends CrudRepository<SpotEntity, Long> {
}
