package com.fur.world_db_demo.layer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fur.world_db_demo.layer.service.CityService;
import com.fur.world_db_demo.model.City;
import com.fur.world_db_demo.pagination.assembler.CityModelAssembler;
import com.fur.world_db_demo.pagination.model.CityModel;

@RestController
@RequestMapping(path = "city")
public class CityController {

	@Autowired
	private CityService cityService;
	@Autowired
	private CityModelAssembler cityModelAssembler;
	@Autowired
	private PagedResourcesAssembler<City> cityPagedResourcesAssembler;

	@GetMapping
	public List<City> getAll() {
		return (ArrayList<City>) cityService.getAll();
	}

	@GetMapping("/paginated")
	public PagedModel<CityModel> getAll(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sort,
			@RequestParam(defaultValue = "ASC") Sort.Direction sortOrder) {
		return cityPagedResourcesAssembler.toModel(cityService.getAll(page, size, sort,sortOrder.toString()), cityModelAssembler);
	}

	@GetMapping("/{id}")
	public City findOne(@PathVariable Integer id) {
		return cityService.findOne(id);
	}

	@GetMapping("/country/{countryCode}")
	public List<City> findByCountryCode(@PathVariable String countryCode) {
		return cityService.findByCountryCode(countryCode);
	}
}
