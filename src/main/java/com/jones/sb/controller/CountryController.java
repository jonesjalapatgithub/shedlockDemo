package com.jones.sb.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jones.sb.model.Country;
import com.jones.sb.repository.CountryRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping(value = "/")
public class CountryController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final CountryRepository countryRepository;
	
	public CountryController(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Country> getAllCountries() {
		logger.info("Getting all countries.");
		return countryRepository.findAll();
	}



}
