package com.fur.world_db_demo.layer.service;

import java.util.List;

import com.fur.world_db_demo.base.CrudBaseModel;
import com.fur.world_db_demo.model.CountryLanguage;

public interface CountryLanguageService extends CrudBaseModel<CountryLanguage, String> {

	List<CountryLanguage> findByCountryCode(String countryCode);
}
