package com.bookmyproperty;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookmyproperty.entity.Location;
import com.bookmyproperty.entity.Property;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class BookmypropertyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BookmypropertyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Location location=new Location();
		location.setAddress1("Khandagiri");
		location.setAddress2("Bhubaneswar");
		location.setLandMark("Pokhariput");
		Property property=new Property();
		property.setId(1l);
		property.setPropertyName("SRIRAM BULDIER PROPERTY 1");
		property.setYouTubleUrl("xyz");
		property.setLocation(location);
		ObjectMapper objectMapper=new ObjectMapper();
		String value=objectMapper.writeValueAsString(property);
		System.out.println(value);
		
	}

}
