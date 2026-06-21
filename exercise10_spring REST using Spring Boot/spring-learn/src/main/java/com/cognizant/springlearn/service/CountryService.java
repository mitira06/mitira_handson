package com.cognizant.springlearn.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@Component
public class CountryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

	/**
	 * Loads the full list of countries from country.xml (countryList bean).
	 */
	@SuppressWarnings("unchecked")
	public List<Country> getAllCountries() {
		LOGGER.info("START");

		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		List<Country> countryList = (List<Country>) context.getBean("countryList", List.class);

		LOGGER.info("END");
		return countryList;
	}

	/**
	 * Returns the country matching the given code, case insensitive.
	 * Throws CountryNotFoundException if no match is found.
	 */
	public Country getCountry(String code) throws CountryNotFoundException {
		LOGGER.info("START");
		LOGGER.debug("code: {}", code);

		List<Country> countryList = getAllCountries();

		Optional<Country> match = countryList.stream()
				.filter(country -> country.getCode().equalsIgnoreCase(code))
				.findFirst();

		if (!match.isPresent()) {
			throw new CountryNotFoundException("Country not found");
		}

		LOGGER.info("END");
		return match.get();
	}

}
