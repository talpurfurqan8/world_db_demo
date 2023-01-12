package com.fur.world_db_demo.layer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fur.world_db_demo.layer.repository.CountryLanguageRepository;
import com.fur.world_db_demo.layer.service.CountryLanguageService;
import com.fur.world_db_demo.model.CountryLanguage;

@Service
public class CountryLanguageServiceImpl implements CountryLanguageService {

	@Autowired
	private CountryLanguageRepository countryLanguageRepository;

	@Override
	public List<CountryLanguage> getAll() {
		return (List<CountryLanguage>) countryLanguageRepository.findAll();
	}

	@Override
	public CountryLanguage findOne(String key) {
		return countryLanguageRepository.findById(key).get();
	}

	@Override
	public List<CountryLanguage> findByCountryCode(String key) {
		return countryLanguageRepository.findByCountryCode(key);
	}

}
