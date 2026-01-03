package dev.parkingApp.repositories;

import dev.parkingApp.entities.RentEntity;
import org.springframework.data.repository.CrudRepository;

public interface RentRepository extends CrudRepository<RentEntity, Long> {
}
