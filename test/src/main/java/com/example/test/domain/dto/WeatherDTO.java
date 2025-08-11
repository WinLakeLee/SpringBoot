package com.example.test.domain.dto;

import java.time.LocalDateTime;

import com.example.test.domain.entity.LocationEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDTO {

	 Integer indentifyNo;
	 Double temperature;
	 LocationEntity location;
	 Double windSpeed;
	 String windDiraction;
	 Double humidity;
	 Integer precipitation;
	 LocalDateTime Time;
	 String Weather;
}
