package com.fur.world_db_demo.pagination.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "cities", itemRelation = "cities")
public class CityModel extends RepresentationModel<CityModel> {
	private long id;
	private String name;
	private String countryCode;
	private String district;
	private long population;

}
