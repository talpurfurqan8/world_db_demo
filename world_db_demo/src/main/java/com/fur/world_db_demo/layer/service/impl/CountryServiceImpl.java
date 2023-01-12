package com.fur.world_db_demo.layer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fur.world_db_demo.layer.repository.CountryRepository;
import com.fur.world_db_demo.layer.service.CountryService;
import com.fur.world_db_demo.model.Country;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public List<Country> getAll() {
		return (List<Country>) countryRepository.findAll();
	}

	@Override
	public Country findOne(String key) {
		return countryRepository.findById(key).get();
	}

}
