package com.example.test.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
	Integer locationId;
	String location;
	Double latitude;
	Double longitude;
}
