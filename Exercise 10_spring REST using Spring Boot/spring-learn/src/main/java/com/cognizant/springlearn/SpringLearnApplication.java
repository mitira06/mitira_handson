package com.cognizant.springlearn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	public static void main(String[] args) {
		LOGGER.info("START");

		SpringApplication.run(SpringLearnApplication.class, args);

		SpringLearnApplication app = new SpringLearnApplication();
		app.displayDate();
		app.displayCountry();
		app.displayCountries();

		LOGGER.info("END");
	}

	/**
	 * Hands on 2: Load SimpleDateFormat bean from date-format.xml and use it
	 * to parse a date string.
	 */
	public void displayDate() {
		LOGGER.info("START");

		ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
		SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);

		try {
			Date date = format.parse("31/12/2018");
			LOGGER.debug("{}", date);
		} catch (ParseException e) {
			LOGGER.error("Error parsing date", e);
		}

		LOGGER.info("END");
	}

	/**
	 * Hands on 4 & 5: Load Country bean from country.xml and display it.
	 * Also fetches the bean a second time to demonstrate singleton scope
	 * (same instance both times, constructor called only once) - change
	 * the "country" bean's scope attribute to "prototype" in country.xml
	 * to see the constructor invoked twice instead.
	 */
	public void displayCountry() {
		LOGGER.info("START");

		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		Country country = context.getBean("country", Country.class);
		Country anotherCountry = context.getBean("country", Country.class);

		LOGGER.debug("Country : {}", country.toString());
		LOGGER.debug("Same instance? {}", (country == anotherCountry));

		LOGGER.info("END");
	}

	/**
	 * Hands on 6: Load the list of all 4 countries from country.xml and
	 * display them.
	 */
	@SuppressWarnings("unchecked")
	public void displayCountries() {
		LOGGER.info("START");

		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		List<Country> countryList = (ArrayList<Country>) context.getBean("countryList", ArrayList.class);

		for (Country country : countryList) {
			LOGGER.debug("Country : {}", country.toString());
		}

		LOGGER.info("END");
	}

}
