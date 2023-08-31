package com.project.carfleet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.carfleet.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByCP(String CP);
}
