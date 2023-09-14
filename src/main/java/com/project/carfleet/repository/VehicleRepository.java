package com.project.carfleet.repository;

import com.project.carfleet.entity.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "SELECT v.* FROM vehicle AS v INNER JOIN model AS m ON v.model_id = m.id INNER JOIN fleet AS f ON v.fleet_id = f.id WHERE f.id = :fleetId AND m.energy = :energy", nativeQuery = true)
    public List<Vehicle> findVehicleByEnergy(@Param("fleetId") Long fleetId, @Param("energy") String energy);

    @Query(value = "SELECT v.* FROM vehicle AS v INNER JOIN model AS m ON v.model_id = m.id INNER JOIN fleet AS f ON v.fleet_id = f.id WHERE f.id = :fleetId AND m.type = :type", nativeQuery = true)
    public List<Vehicle> findVehicleByType(@Param("fleetId") Long fleetId, @Param("type") String type);

    @Query(value = "SELECT v.* FROM vehicle AS v INNER JOIN model AS m ON v.model_id = m.id INNER JOIN fleet AS f ON v.fleet_id = f.id WHERE f.id = :fleetId AND m.type = :type AND m.energy = :energy", nativeQuery = true)
    public List<Vehicle> findVehicleByTypeAndEnergy(@Param("fleetId") Long fleetId, @Param("type") String type, @Param("energy") String energy);

    @Query(value = "SELECT v.* FROM vehicle AS v INNER JOIN fleet AS f ON v.fleet_id = f.id WHERE f.id = :fleetId", nativeQuery = true)
    public List<Vehicle> findVehicleByFleet(@Param("fleetId") Long fleetId);
}
