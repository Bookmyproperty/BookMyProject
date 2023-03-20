package com.bookmyproperty.dto;

import java.time.LocalDateTime;

import com.bookmyproperty.entity.Property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyResponse {
	
	Property property;	
	LocalDateTime localDateTime=LocalDateTime.now();

}
