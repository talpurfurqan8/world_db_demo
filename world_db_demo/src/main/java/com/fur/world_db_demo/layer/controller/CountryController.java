package com.fur.world_db_demo.layer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fur.world_db_demo.layer.service.CountryService;
import com.fur.world_db_demo.model.Country;

@RestController
@RequestMapping(path = "country")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping
	public List<Country> getAll() {
		return (ArrayList<Country>) countryService.getAll();
	}

	@GetMapping("/{countryCode}")
	public Country findOne(@PathVariable String countryCode) {
		return countryService.findOne(countryCode);
	}

}
