package com.fur.world_db_demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity(name = "COUNTRYLANGUAGE")
@Data
public class CountryLanguage {

	@Id
	private String language;

	@Column(name = "COUNTRYCODE")
	private String countryCode;

	@Column(name = "ISOFFICIAL")
	private String IsOfficial;

	private Float percentage;
	
	@JsonIgnore // [to remove |Cannot call sendError() after the response has been committed]
	@ManyToOne
	@JoinColumn(name = "COUNTRYCODE", updatable = false, insertable = false)
	private Country country;

}
