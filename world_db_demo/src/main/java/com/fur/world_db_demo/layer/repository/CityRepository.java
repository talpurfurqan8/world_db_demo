package com.fur.world_db_demo.layer.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fur.world_db_demo.model.City;

@Repository
public interface CityRepository extends PagingAndSortingRepository<City, Integer> {
	List<City> findByCountryCode(String countryCode);
}
