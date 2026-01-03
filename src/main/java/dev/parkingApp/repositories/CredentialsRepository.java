package dev.parkingApp.repositories;

import dev.parkingApp.entities.CredentialsEntity;
import org.springframework.data.repository.CrudRepository;

public interface CredentialsRepository extends CrudRepository<CredentialsEntity, Long> {
}
