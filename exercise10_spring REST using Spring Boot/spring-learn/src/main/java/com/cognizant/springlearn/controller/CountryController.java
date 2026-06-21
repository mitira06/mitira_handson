package com.cognizant.springlearn.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@RestController
@RequestMapping("/countries")
public class CountryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

	@Autowired
	private CountryService countryService;

	public CountryController() {
		LOGGER.debug("Inside CountryController Constructor.");
	}

	/**
	 * Kept at the original /country (singular) URL for backward
	 * compatibility with Doc 2's Hands-on, which loads the India bean
	 * directly from country.xml.
	 */
	@GetMapping("/india")
	public Country getCountryIndia() {
		LOGGER.info("START");

		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		Country country = context.getBean("country", Country.class);

		LOGGER.info("END");
		return country;
	}

	/**
	 * Hands on: REST - Get all countries
	 * GET /countries
	 */
	@GetMapping
	public List<Country> getAllCountries() {
		LOGGER.info("START");

		List<Country> countryList = countryService.getAllCountries();

		LOGGER.info("END");
		return countryList;
	}

	/**
	 * Hands on: REST - Get country based on country code (case insensitive)
	 * GET /countries/{code}
	 */
	@GetMapping("/{code}")
	public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
		LOGGER.info("START");
		LOGGER.debug("code: {}", code);

		Country country = countryService.getCountry(code);

		LOGGER.info("END");
		return country;
	}

	/**
	 * Hands on: Create RESTful Web Service to handle POST request of Country
	 * POST /countries
	 * Validation handled globally via GlobalExceptionHandler + @Valid.
	 */
	@PostMapping
	public Country addCountry(@RequestBody @Valid Country country) {
		LOGGER.info("START");
		LOGGER.debug("country: {}", country);

		LOGGER.info("END");
		return country;
	}

}
