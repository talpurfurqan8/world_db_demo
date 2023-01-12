package com.fur.world_db_demo.layer.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fur.world_db_demo.base.CrudBaseModel;
import com.fur.world_db_demo.model.City;

public interface CityService extends CrudBaseModel<City, Integer> {
	List<City> findByCountryCode(String countryCode);

	Page<City> getAll(Integer page, Integer size, String sort, String sortOrder);
}
