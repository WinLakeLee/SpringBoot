package com.example.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.test.domain.entity.LocationEntity;


public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {
	Optional<LocationEntity> findIdByLocationName(String locationName);
}
