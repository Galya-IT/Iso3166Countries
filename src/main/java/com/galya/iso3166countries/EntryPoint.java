package com.galya.iso3166countries;

import java.io.IOException;
import java.util.Collection;

import org.apache.http.client.ClientProtocolException;

import com.galya.iso3166countries.model.CountryIso3166;
import com.galya.iso3166countries.persistence.CountriesDataStorage;
import com.galya.iso3166countries.persistence.CountriesHsqldbStorage;
import com.galya.iso3166countries.utils.CommonUtils;

public class EntryPoint {

    public static void main(String[] args) {

        try {
            Collection<CountryIso3166> countriesFromWiki = Iso3166CountriesFetcher.fetchFromWiki();
            CommonUtils.printAllInConsole(countriesFromWiki, "Countries From Wiki");
            
            CountriesDataStorage countriesDataStorage = new CountriesHsqldbStorage();
            countriesDataStorage.addMultipleCountries(countriesFromWiki);
            
            Collection<CountryIso3166> countriesFromDatabase = countriesDataStorage.getAllCountries();
            CommonUtils.printAllInConsole(countriesFromDatabase, "Countries From Database");
            
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
