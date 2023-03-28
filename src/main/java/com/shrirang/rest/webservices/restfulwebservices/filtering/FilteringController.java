package com.shrirang.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;



@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {

		SomeBean someBean=new SomeBean("Shree", "pass123", "India", "MH");
		MappingJacksonValue mappingJacksonValue=new  MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
		
		
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue filteringList() {

		 List<SomeBean> list = Arrays.asList(new SomeBean("Shree", "pass123", "India", "MH"),
				
				            new SomeBean("Shyam", "pass456", "India", "MH"));
		 
		 MappingJacksonValue mappingJacksonValue=new  MappingJacksonValue(list);
			SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field3","field4");
			FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
			mappingJacksonValue.setFilters(filters);
			
			return mappingJacksonValue;
	}		
				
}
