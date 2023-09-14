package com.project.carfleet.repository;

import com.project.carfleet.entity.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "SELECT v.* FROM vehicle AS v INNER JOIN model AS m ON v.model_id = m.id WHERE m.energy = :energy", nativeQuery = true)
    public List<Vehicle> findVehicleByEnergy(@Param("energy") String energy);

    @Query(value = "SELECT v.* FROM vehicle AS v INNER JOIN model AS m ON v.model_id = m.id WHERE m.type = :type", nativeQuery = true)
    public List<Vehicle> findVehicleByType(@Param("type") String type);

    @Query(value = "SELECT v.* FROM vehicle AS v INNER JOIN model AS m ON v.model_id = m.id WHERE m.type = :type AND m.energy = :energy", nativeQuery = true)
    public List<Vehicle> findVehicleByTypeAndEnergy(@Param("type") String type, @Param("energy") String energy);
}
