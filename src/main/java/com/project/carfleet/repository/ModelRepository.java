package com.project.carfleet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.carfleet.entity.Model;
@Repository
public interface ModelRepository extends JpaRepository<Model, Long>{
    
}
