package dev.parkingApp.repositories;

import dev.parkingApp.entities.SpotEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SpotRepository extends CrudRepository<SpotEntity, Long> {

//    @Query(
//            value = "",
//            nativeQuery = true)
//    SpotEntity addSpot(SpotEntity spot, @Param("ownerId") Long ownerId);
}
