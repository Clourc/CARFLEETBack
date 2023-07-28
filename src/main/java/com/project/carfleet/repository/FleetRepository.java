package com.project.carfleet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.carfleet.entity.Fleet;

public interface FleetRepository extends JpaRepository<Fleet, Long> {

}
