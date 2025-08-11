package com.example.test.service;

import org.springframework.stereotype.Service;

import com.example.test.domain.dto.LocationDTO;
import com.example.test.domain.entity.LocationEntity;
import com.example.test.repository.LocationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class LocationService {

	private final LocationRepository locationRepository;
	
	public LocationDTO findLocation(Integer id) {
		LocationEntity entity = locationRepository.findById(id).orElseThrow();
		LocationDTO dto = EntityToDTO(entity);
		return dto;
	}
	
	public LocationEntity DTOToEntity(LocationDTO dto) {
		LocationEntity entity = LocationEntity.builder()
				.locationId(dto.getLocationId())
				.locationName(dto.getLocation())
				.latitude(dto.getLatitude())
				.longitude(dto.getLongitude())
				.build();
		return entity;
	}
	
	public LocationDTO EntityToDTO(LocationEntity entity) {
		LocationDTO dto = LocationDTO.builder()
				.locationId(entity.getLocationId())
				.location(entity.getLocationName())
				.latitude(entity.getLatitude())
				.longitude(entity.getLongitude())
				.build();
		return dto;
	}
}
