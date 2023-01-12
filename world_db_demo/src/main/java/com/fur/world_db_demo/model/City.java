package com.fur.world_db_demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fur.world_db_demo.pagination.model.CityModel;

import lombok.Data;
import lombok.ToString;

@Entity(name = "city")
@Data
@ToString
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "COUNTRYCODE")
	private String countryCode;

	@Column(name = "DISTRICT")
	private String district;

	@Column(name = "POPULATION")
	private long population;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "COUNTRYCODE", updatable = false, insertable = false)
	private Country country;

	public CityModel getCityModel() {
		return new CityModel(id, name, countryCode, district, population);
	}
}
