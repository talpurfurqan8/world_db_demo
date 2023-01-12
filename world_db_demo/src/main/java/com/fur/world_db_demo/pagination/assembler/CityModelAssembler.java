package com.fur.world_db_demo.pagination.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.fur.world_db_demo.layer.controller.CityController;
import com.fur.world_db_demo.model.City;
import com.fur.world_db_demo.pagination.model.CityModel;

@Component
public class CityModelAssembler extends RepresentationModelAssemblerSupport<City, CityModel> {

	public CityModelAssembler() {
		super(CityController.class, CityModel.class);
	}

	@Override
	public CityModel toModel(City entity) {
		return entity.getCityModel();
	}

}
