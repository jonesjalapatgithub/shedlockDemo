package com.sunbeam.sb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sunbeam.sb.model.Country;

public interface CountryRepository extends MongoRepository<Country, String> {

}