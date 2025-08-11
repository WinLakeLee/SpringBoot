package com.example.test.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.test.domain.dto.WeatherDTO;
import com.example.test.domain.entity.LocationEntity;
import com.example.test.domain.entity.WeatherEntity;
import com.example.test.repository.LocationRepository;
import com.example.test.repository.WeatherRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class WeatherService {

	private final WeatherRepository repository;
	private final LocationRepository locationRepository;
	
	public String WeatherInput(WeatherDTO dto) {
        LocationEntity location;

        // 1. Location ID가 존재하면 기존 Location 불러오기
        if (dto.getLocation() != null && dto.getLocation().getLocationId() != null) {
            location = locationRepository.findById(dto.getLocation().getLocationId())
                    .orElseThrow(() -> new RuntimeException("해당 Location 없음"));
        }
        // 2. ID가 없으면 새 Location 저장
        else {
            location = locationRepository.save(dto.getLocation());
        }
		WeatherEntity entity = WeatherEntity.builder()
				.temperature(dto.getTemperature())
				.location(location)
				.windSpeed(dto.getWindSpeed())
				.windDiraction(dto.getWindDiraction())
				.humidity(dto.getHumidity())
				.precipitationProbability(dto.getPrecipitation())
				.Time(dto.getTime())
				.Weather(dto.getWeather())
				.build();
		repository.save(entity);
		return "날씨 정보 저장 완료";
	}
	
	public List<WeatherDTO> findWeatherAll() {
		List<WeatherDTO> weatherList = new ArrayList<>();
		List<WeatherEntity> entityList = repository.findAll();
		for (WeatherEntity entity : entityList) {
			
			weatherList.add(EntityToDTO(entity));
		}
		return weatherList;
	}
	
	public WeatherDTO findWeatherById(Integer id) {
		return EntityToDTO(repository.findById(id).orElseThrow());
	}
	
	public List<WeatherDTO> findAllByLocationName(String locationName) {
		LocationEntity locEntity = locationRepository.findIdByLocationName(locationName).orElseThrow();
		List<WeatherEntity> entityList = repository.findAllByLocation(locEntity).orElseThrow();
		List<WeatherDTO> weatherList = new ArrayList<>();
		for (WeatherEntity entity : entityList) {
			weatherList.add(EntityToDTO(entity));
		}
		return weatherList;	
	}
	
	public WeatherDTO updateWeather(WeatherDTO dto) {
		WeatherEntity entity = DTOToEntity(dto);
		repository.save(entity);
		return EntityToDTO(entity);
	}
	
	public String deleteWeather(Integer id) {
		repository.delete(repository.findById(id).orElseThrow());
		return "삭제 성공";
	}
	
	private WeatherEntity DTOToEntity(WeatherDTO dto) {
		WeatherEntity entity  = WeatherEntity.builder()
				.indentifyNo(dto.getIndentifyNo())
				.location(dto.getLocation())
				.temperature(dto.getTemperature())
				.windSpeed(dto.getWindSpeed())
				.windDiraction(dto.getWindDiraction())
				.humidity(dto.getHumidity())
				.precipitationProbability(dto.getPrecipitation())
				.Time(dto.getTime())
				.Weather(dto.getWeather())
				.build();
		return entity;
	}
	
	private WeatherDTO EntityToDTO(WeatherEntity entity) {
		WeatherDTO dto = WeatherDTO.builder()
				.indentifyNo(entity.getIndentifyNo())
				.location(entity.getLocation())
				.temperature(entity.getTemperature())
				.windSpeed(entity.getWindSpeed())
				.windDiraction(entity.getWindDiraction())
				.humidity(entity.getHumidity())
				.precipitation(entity.getPrecipitationProbability())
				.Time(entity.getTime())
				.Weather(entity.getWeather())
				.build();
		return dto;
	}
}
