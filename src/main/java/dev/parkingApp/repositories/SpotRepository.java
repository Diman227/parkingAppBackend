package dev.parkingApp.repositories;

import dev.parkingApp.entities.SpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpotRepository extends JpaRepository<SpotEntity, Long> {

//    @Query(
//            value = "",
//            nativeQuery = true)
//    SpotEntity addSpot(SpotEntity spot, @Param("ownerId") Long ownerId);

    @Query(value = "SELECT s FROM SpotEntity s WHERE s.owner.id = :userId")
    public List<SpotEntity> getUserOwnedSpots(@Param("userId") Long userId);
}
