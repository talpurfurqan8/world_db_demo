package com.fur.world_db_demo.layer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fur.world_db_demo.api.constants.HomeApiConstants;
import com.fur.world_db_demo.model.Greeting;

@RestController
@RequestMapping(path="home")
public class HomeController {

	private static final String template = "Hello, %s!";
	
	@GetMapping(path = HomeApiConstants.GREETING)
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(String.format(template, name));
	}
}
