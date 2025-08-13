package com.example.test.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.Check;

import com.example.test.TestApplication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weather")
@EntityListeners(TestApplication.class)
@Check(constraints = "temperature > -273")
@Check(constraints = "wind_speed >= 0")
@Check(constraints = "humidity >= 0")
@Check(constraints = "precipitation_probability >= 0 and precipitation_probability <= 100")
public class WeatherEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer indentifyNo;
	
	@Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
	@Min(-273)
	private Double temperature;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id", nullable = false)
	private LocationEntity location;
	
	@Column(nullable = false, columnDefinition = "DECIMAL(8,3)")
	@Min(0)
	@Builder.Default
	private Double windSpeed = 0.0;
	
	@Column(nullable = false)
	private String windDiraction;
	
	@Column(nullable = false)
	@Min(0)
	@Builder.Default
	private Double humidity = 0.0;
	
	@Column(nullable = false)
	@Min(0)
	@Max(100)
	@Builder.Default
	private Integer precipitationProbability = 0;
	
	@Column(name= "weather_time", nullable = false)
	private LocalDateTime time;
	
	@Column(nullable = false)
	private String weather;

}