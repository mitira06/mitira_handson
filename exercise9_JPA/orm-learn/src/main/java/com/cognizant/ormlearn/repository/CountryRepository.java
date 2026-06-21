package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Search box: matches countries whose name CONTAINS the given text anywhere (e.g. "ou" -> Bouvet Island, Djibouti...)
    List<Country> findByNameContaining(String text);

    // Same as above, but results sorted by name ascending (alphabet index feature)
    List<Country> findByNameContainingOrderByNameAsc(String text);

    // Alphabet index click: countries whose name STARTS WITH the given letter (e.g. "Z" -> Zambia, Zimbabwe)
    List<Country> findByNameStartingWith(String letter);
}
