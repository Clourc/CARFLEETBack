package com.project.carfleet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.carfleet.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
}
