package dev.parkingApp.repositories;

import dev.parkingApp.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query(value = "SELECT b FROM BookingEntity b WHERE b.renter.id = :userId " +
            "AND b.endAt > :currentDate")
    List<BookingEntity> getUserBookings(@Param("userId") Long userId, @Param("currentDate") LocalDateTime currentDate);

    @Query(value = "select b from BookingEntity b where b.renter.id = :userId " +
            "AND b.startAt < :currentDate AND b.endAt > :currentDate")
    List<BookingEntity> getUserActiveBookings(@Param("userId") Long userId, @Param("currentDate") LocalDateTime currentDate);

    @Query(value = "select b from BookingEntity b where b.renter.id = :userId " +
            "AND b.startAt > :currentDate")
    List<BookingEntity> getUserPlannedBookings(@Param("userId") Long userId, @Param("currentDate") LocalDateTime currentDate);

    @Query(value = "select b from BookingEntity b where b.renter.id = :userId " +
            "AND b.endAt < :currentDate")
    List<BookingEntity> getUserPastBookings(@Param("userId") Long userId, @Param("currentDate") LocalDateTime currentDate);

}
