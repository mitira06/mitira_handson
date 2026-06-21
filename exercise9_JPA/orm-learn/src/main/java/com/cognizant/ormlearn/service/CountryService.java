package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    // Search box: countries whose name contains the given text
    @Transactional
    public List<Country> searchCountriesByName(String text) {
        return countryRepository.findByNameContaining(text);
    }

    // Search box, results sorted alphabetically
    @Transactional
    public List<Country> searchCountriesByNameSorted(String text) {
        return countryRepository.findByNameContainingOrderByNameAsc(text);
    }

    // Alphabet index: countries whose name starts with the given letter
    @Transactional
    public List<Country> findCountriesStartingWith(String letter) {
        return countryRepository.findByNameStartingWith(letter);
    }

    // Doc 1, Hands-on 6: find a country based on country code
    @Transactional
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        Optional<Country> result = countryRepository.findById(countryCode);
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country not found for code: " + countryCode);
        }
        Country country = result.get();
        return country;
    }

    // Doc 1, Hands-on 7: add a new country
    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    // Doc 1, Hands-on 8: update a country's name based on its code
    @Transactional
    public void updateCountry(String code, String name) throws CountryNotFoundException {
        Optional<Country> result = countryRepository.findById(code);
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country not found for code: " + code);
        }
        Country country = result.get();
        country.setName(name);
        countryRepository.save(country);
    }

    // Doc 1, Hands-on 9: delete a country based on code
    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }
}
