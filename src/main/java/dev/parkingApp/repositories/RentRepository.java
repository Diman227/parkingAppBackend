package dev.parkingApp.repositories;

import dev.parkingApp.entities.BookingEntity;
import org.springframework.data.repository.CrudRepository;

public interface RentRepository extends CrudRepository<BookingEntity, Long> {
}
