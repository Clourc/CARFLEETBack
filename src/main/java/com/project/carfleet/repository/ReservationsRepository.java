package com.project.carfleet.repository;

import com.project.carfleet.entity.Reservations;
import com.project.carfleet.entity.Vehicle;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Long> {

    @Query(value = "SELECT r.* FROM reservations AS r INNER JOIN vehicle AS v ON r.vehicle_id = v.id WHERE r.vehicle_id = :vehicleId ORDER BY start_date ASC", nativeQuery = true)
    public List<Reservations> findResaByVehicleOrderByASC(@Param("vehicleId") Long vehicleId);

    @Query(value = "SELECT r.* FROM reservations AS r INNER JOIN vehicle AS v ON r.vehicle_id = v.id WHERE r.vehicle_id = :vehicleId ORDER BY start_date DESC", nativeQuery = true)
    public List<Reservations> findResaByVehicleOrderByDESC(@Param("vehicleId") Long vehicleId);

    @Query(value = "SELECT r.* FROM reservations AS r INNER JOIN user AS u ON r.user_id = u.id WHERE r.user_id = :userId ORDER BY start_date ASC", nativeQuery = true)
    public List<Reservations> findResaByUserOrderByASC(@Param("userId") Long userId);

    @Query(value = "SELECT r.* FROM reservations AS r INNER JOIN user AS u ON r.user_id = u.id WHERE r.user_id = :userId ORDER BY start_date DESC", nativeQuery = true)
    public List<Reservations> findResaByUserOrderByDESC(@Param("userId") Long userId);

    @Query(value = "SELECT r.*, f.id AS fleetId FROM reservations AS r INNER JOIN user AS u ON r.user_id = u.id INNER JOIN fleet AS f ON u.fleet_id = f.id WHERE f.id = :fleetId", nativeQuery = true)
    public List<Reservations> findAllResaByFleet(@Param("fleetId") Long fleetId);
}
