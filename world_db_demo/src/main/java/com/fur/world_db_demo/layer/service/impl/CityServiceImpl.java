package com.fur.world_db_demo.layer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fur.world_db_demo.layer.repository.CityRepository;
import com.fur.world_db_demo.layer.service.CityService;
import com.fur.world_db_demo.model.City;
import com.fur.world_db_demo.utils.CommonUtils;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepo;

	@Override
	public List<City> getAll() {
		return (List<City>) cityRepo.findAll();
	}

	@Override
	public Page<City> getAll(Integer page, Integer size, String sort, String sortOrder) {
		Pageable paging = PageRequest.of(page, size, Sort.by(CommonUtils.createSortOrder(sort, sortOrder)));
		Page<City> pagedResult = cityRepo.findAll(paging);
		return pagedResult;
	}

	@Override
	public City findOne(Integer key) {
		return cityRepo.findById(key).get();
	}

	@Override
	public List<City> findByCountryCode(String key) {
		return cityRepo.findByCountryCode(key);
	}

}
