package com.galya.iso3166countries.persistence;

import java.util.Collection;

import com.galya.iso3166countries.model.CountryIso3166;

public interface CountriesDataStorage {
    
    Collection<CountryIso3166> getAllCountries();
    
    void addCountry(CountryIso3166 countryIso3166);
    
    void addMultipleCountries(Collection<CountryIso3166> countries);
    
}
