package com.example.test.domain.entity;

import org.hibernate.annotations.Check;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location")
@Check(constraints = "latitude >= -180 and latitude <= 180")
@Check(constraints = "longitude >= -180 and longitude <= 180")
public class LocationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private Integer locationId;
	
	@Column(nullable = false, unique = true)
	private String locationName;
	
	@Column(nullable = false, columnDefinition = "DECIMAL(25,20)")
	@Min(-180)
	@Max(180)
	private Double latitude;
	
	@Column(nullable = false, columnDefinition = "DECIMAL(25,20)")
	@Min(-180)
	@Max(180)
	private Double longitude;
}
