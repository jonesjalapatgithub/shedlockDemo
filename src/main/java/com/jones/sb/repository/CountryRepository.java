package com.jones.sb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jones.sb.model.Country;

public interface CountryRepository extends MongoRepository<Country, String> {

}