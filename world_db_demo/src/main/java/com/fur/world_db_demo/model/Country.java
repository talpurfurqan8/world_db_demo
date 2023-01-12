package com.fur.world_db_demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity(name = "country")
@Data
public class Country {

	@Id
	private String code;
	private String name;
	private String continent;
	private String region;

	@Column(name = "SURFACEAREA")
	private Float surfaceArea;

	@Column(name = "INDEPYEAR")
	private Integer indepYear;

	private Long population;

	@Column(name = "LIFEEXPECTANCY")
	private Float lifeExpectancy;

	@Column(name = "GNP")
	private Float gnp;

	@Column(name = "GNPOLD")
	private Float gNPOld;

	@Column(name = "LOCALNAME")
	private String localName;

	@Column(name = "GOVERNMENTFORM")
	private String governmentForm;

	@Column(name = "HEADOFSTATE")
	private String headOfState;

	private Integer capital;

	@Column(name = "CODE2")
	private String code2;

	/** one to many | languages **/
	@OneToMany(mappedBy = "country")
	// @JoinColumn(name = "COUNTRYCODE")
	private List<CountryLanguage> countryLanguages;
	
	/** one to many | cities **/
	@OneToMany(mappedBy = "country")
	private List<City> cities;

}
