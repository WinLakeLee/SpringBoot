package com.example.test.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.domain.dto.WeatherDTO;
import com.example.test.service.WeatherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/weather")
public class WeatherController {

	private final WeatherService weatherService;
	
	@PostMapping("")
	public ResponseEntity<String> inputWeather(@RequestBody WeatherDTO dto) {
		String result = weatherService.WeatherInput(dto);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("all")
	public ResponseEntity<List<WeatherDTO>> findWeatherAll() {
		List<WeatherDTO> weatherList = weatherService.findWeatherAll();
		return ResponseEntity.ok(weatherList);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<WeatherDTO> findWeatherById(@PathVariable Integer id) {
		WeatherDTO result = weatherService.findWeatherById(id);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("region")
	public ResponseEntity<List<WeatherDTO>> findWeatherByLocation(@RequestParam("location") String locationName){
		List<WeatherDTO> WeatherLocationList = weatherService.findAllByLocationName(locationName);
		return ResponseEntity.ok(WeatherLocationList);
	}
	
	@PutMapping("")
	public ResponseEntity<WeatherDTO> updateWeather(@RequestBody WeatherDTO dto) {
		WeatherDTO updateDTO = weatherService.updateWeather(dto);
		return ResponseEntity.ok(updateDTO);
	}
	
	@DeleteMapping("")
	public ResponseEntity<String> deleteWeather(@RequestParam("id") Integer id) {
		String result = weatherService.deleteWeather(id);
		return ResponseEntity.ok(result);
	}
}
