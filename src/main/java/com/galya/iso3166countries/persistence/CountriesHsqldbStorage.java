package com.galya.iso3166countries.persistence;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.galya.iso3166countries.model.CountryIso3166;

public class CountriesHsqldbStorage implements CountriesDataStorage {
    
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Iso3166Countries");

    public CountriesHsqldbStorage() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<CountryIso3166> getAllCountries() {
        Collection<CountryIso3166> allCountries = null;
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        Query query = entityManager.createQuery("SELECT c FROM CountryIso3166 c");
        
        allCountries = (Collection<CountryIso3166>) query.getResultList();
        
        entityManager.close();
        
        return allCountries;
    }

    @Override
    public void addCountry(CountryIso3166 countryIso3166) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(countryIso3166);
        entityManager.close();
    }

    @Override
    public void addMultipleCountries(Collection<CountryIso3166> countries) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.setFlushMode(FlushModeType.COMMIT);
        
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        
        for (CountryIso3166 country : countries) {
            entityManager.persist(country);
        }
        
        entityTransaction.commit();
        entityManager.close();
    }

}
