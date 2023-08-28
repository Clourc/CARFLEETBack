package com.project.carfleet.repository;

import com.project.carfleet.entity.Vehicule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long>{

    @Query(value = "SELECT v.* FROM vehicule AS v INNER JOIN model AS m ON v.model_id = m.id WHERE m.energy = :energy", nativeQuery = true)
    public List<Vehicule> findVehiculeByEnergy(@Param("energy") String energy);

    @Query(value  = "SELECT v.* FROM vehicule AS v INNER JOIN model AS m ON v.model_id = m.id WHERE m.type = :type", nativeQuery = true)
    public List<Vehicule> findVehiculeByType(@Param("type") String type);

    @Query(value = "SELECT v.* FROM vehicule AS v INNER JOIN model AS m ON v.model_id = m.id WHERE m.type = :type AND m.energy = :energy", nativeQuery = true)
    public List<Vehicule> findVehiculeByTypeAndEnergy(@Param("type") String type, @Param("energy") String energy);
}
