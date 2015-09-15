package com.galya.iso3166countries.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class CountryIso3166 {

    @Id
    @Column(name = "country_code", updatable = false, unique = true, nullable = false)
    private String code;

    @Column(name = "country_name")
    private String name;

    @Column(name = "country_year_standard_assigned")
    private int yearForStandardAssigned;

    @Column(name = "country_top_level_domain")
    private String countryCodeTopLevelDomain;

    public CountryIso3166() {

    }

    public CountryIso3166(String code, String name, int yearForStandardAssigned, String countryCodeTopLevelDomain)
            throws Exception {
        this();

        setCode(code);
        setName(name);
        setYearForStandardAssigned(yearForStandardAssigned);
        setCountryCodeTopLevelDomain(countryCodeTopLevelDomain);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getYearForStandardAssigned() {
        return yearForStandardAssigned;
    }

    public String getCountryCodeTopLevelDomain() {
        return countryCodeTopLevelDomain;
    }

    public void setCode(String code) throws Exception {
        if (code == null) {
            throw new Exception("Code cannot be null.");
        }
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearForStandardAssigned(int yearForStandardAssigned) {
        this.yearForStandardAssigned = yearForStandardAssigned;
    }

    public void setCountryCodeTopLevelDomain(String countryCodeTopLevelDomain) {
        this.countryCodeTopLevelDomain = countryCodeTopLevelDomain;
    }

    /**
     * Two countries are equal when they have the same country code.
     */
    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;

        if (obj instanceof CountryIso3166) {
            CountryIso3166 anotherCountryIso3166 = (CountryIso3166) obj;
            isEqual = this.getCode().equals(anotherCountryIso3166.getCode());
        }

        return isEqual;
    }

    @Override
    public String toString() {
        String result = "";

        if (code != null) {
            result += String.format("Code: %s", code);
        }
        if (name != null) {
            if (!result.equals("")) {
                result += " | ";
            }
            result += String.format("Name: %-36s", name);
        }
        if (yearForStandardAssigned != 0) {
            if (!result.equals("")) {
                result += " | ";
            }
            result += String.format("Year: %s", yearForStandardAssigned);
        }
        if (countryCodeTopLevelDomain != null) {
            if (!result.equals("")) {
                result += " | ";
            }
            result += String.format("Domain: %s", countryCodeTopLevelDomain);
        }

        return result;
    }
}
