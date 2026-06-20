package com.cognizant.ormlearn.service.exception;

// Doc 1, Hands-on 6: thrown when a country lookup by code finds no matching row
public class CountryNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CountryNotFoundException(String message) {
        super(message);
    }
}
