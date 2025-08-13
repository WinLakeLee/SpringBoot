package com.example.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.test.domain.entity.LocationEntity;
import com.example.test.domain.entity.WeatherEntity;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Integer> {
	Optional<List<WeatherEntity>> findAllByLocation(LocationEntity location);
	List<WeatherEntity> findAllByLocationAndTime(LocationEntity location, LocalDateTime time);
}
