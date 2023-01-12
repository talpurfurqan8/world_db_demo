package com.fur.world_db_demo.layer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fur.world_db_demo.model.CountryLanguage;

@Repository
public interface CountryLanguageRepository extends CrudRepository<CountryLanguage, String> {

	List<CountryLanguage> findByCountryCode(String countryCode);
}
