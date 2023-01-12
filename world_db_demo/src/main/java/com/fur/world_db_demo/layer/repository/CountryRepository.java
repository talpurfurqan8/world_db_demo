package com.fur.world_db_demo.layer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fur.world_db_demo.model.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, String> {
}
