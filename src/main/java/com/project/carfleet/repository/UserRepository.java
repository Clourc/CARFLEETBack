package com.project.carfleet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.carfleet.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  
}
