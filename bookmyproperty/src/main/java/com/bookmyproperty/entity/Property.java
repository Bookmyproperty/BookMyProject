package com.bookmyproperty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {
	
     private Long id;
     private String propertyName;
     private String youTubleUrl;
     private Location location;

}
