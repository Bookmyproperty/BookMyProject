package com.bookmyproperty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyproperty.entity.Property;
import com.bookmyproperty.producer.PropertyProducer;

@RequestMapping("property")
@RestController
public class PropertyController {
	
	@Autowired
	private PropertyProducer propertyProducer;
	
	@PostMapping("register")
	public Property registerProperty(@RequestBody Property property){
		return propertyProducer.register(property);
	}
	

}
