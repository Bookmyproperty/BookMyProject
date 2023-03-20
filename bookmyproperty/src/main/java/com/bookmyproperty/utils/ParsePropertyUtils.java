package com.bookmyproperty.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookmyproperty.entity.Property;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ParsePropertyUtils {
	
	
	
	public static String getPropertyAsString(Property property) {
		try {
			ObjectMapper mapper=new ObjectMapper();
			return mapper.writeValueAsString(property);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
