package com.project.carfleet.repository;
import com.project.carfleet.entity.Reservations;
import com.project.carfleet.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
    
    @Query(value = "SELECT r.* FROM reservations AS r INNER JOIN vehicle AS v ON r.vehicle_id = v.id WHERE r.vehicle_id = :vehicleId", nativeQuery = true)
    public List<Reservations> findResaByVehicle(@Param("vehicleId") Long vehicleId);
}
