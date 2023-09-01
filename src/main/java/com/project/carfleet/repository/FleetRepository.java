package com.project.carfleet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.carfleet.dto.FleetDto;
import com.project.carfleet.entity.Fleet;

public interface FleetRepository extends JpaRepository<Fleet, Long> {

    @Query("SELECT new com.project.carfleet.dto.FleetDto(id, place) FROM Fleet")
    List<FleetDto> findAllFleet();

    @Query("SELECT new com.project.carfleet.dto.FleetDto(id, place) FROM Fleet  WHERE id = ?1")
    FleetDto findFleetDtoById(long id);

}
