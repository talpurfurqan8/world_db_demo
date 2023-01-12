package com.fur.world_db_demo.layer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fur.world_db_demo.layer.service.CountryLanguageService;
import com.fur.world_db_demo.model.CountryLanguage;

@RestController
@RequestMapping(path = "countryLanguage")
public class CountryLanguageController {

	@Autowired
	private CountryLanguageService countryLanguageService;

	@GetMapping
	public List<CountryLanguage> getAll() {
		return (ArrayList<CountryLanguage>) countryLanguageService.getAll();
	}

	@GetMapping("/{countryCode}")
	public List<CountryLanguage> findByCountryCode(@PathVariable String countryCode) {
		return countryLanguageService.findByCountryCode(countryCode);
	}
}
