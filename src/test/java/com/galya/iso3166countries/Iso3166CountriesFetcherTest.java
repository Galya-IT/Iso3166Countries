package com.galya.iso3166countries;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.galya.iso3166countries.model.CountryIso3166;

public class Iso3166CountriesFetcherTest {

    @Test
    public void fetchFromWikiHtmlTest() {
        final String HTML = "<!DOCTYPEhtml><html><head><metacharset=\"UTF-8\"><title>Countries</title></head><body><tableclass=\"wikitablesortable\">   <tr><td><tt>ZW</tt></td><td><ahref=\"/wiki/Zimbabwe\"title=\"Zimbabwe\">Zimbabwe</a></td><td>1980</td><td><ahref=\"/wiki/.zw\"title=\".zw\">.zw</a></td><td><spanclass=\"nowrap\"><ahref=\"/wiki/ISO_3166-2:ZW\"title=\"ISO3166-2:ZW\">ISO3166-2:ZW</a></span></td><td>Namechangedfrom<i><ahref=\"/wiki/Rhodesia\"title=\"Rhodesia\">SouthernRhodesia</a></i>(<tt>RH</tt>)</td> </tr></table><tableclass=\"wikitablesortable\"> <tr><td><tt>ZA</tt></td><td><ahref=\"/wiki/South_Africa\"title=\"SouthAfrica\">SouthAfrica</a></td><td>1974</td><td><ahref=\"/wiki/.za\"title=\".za\">.za</a></td><td><spanclass=\"nowrap\"><ahref=\"/wiki/ISO_3166-2:ZA\"title=\"ISO3166-2:ZA\">ISO3166-2:ZA</a></span></td><td>Codetakenfromnamein<ahref=\"/wiki/Dutch_language\"title=\"Dutchlanguage\">Dutch</a>:<i><spanlang=l\"xml:lang=\"nl\">Zuid-Afrika</span></i></td>    </tr></table><ulclass=\"wikitablesortable\">    <li>Somesortablestuff1</li> <li>Somesortablestuff2</li> <li>Somesortablestuff3</li></ul></body></html>";

        try {
            final int EXPECTED_RESULT_COUNTRY_COUNT = 1;
            final CountryIso3166 EXPECTED_RESULT_COUNTRY = new CountryIso3166("ZW", "Zimbabwe", 1980, ".zw");
            
            List<CountryIso3166> fetchedCountries = (List<CountryIso3166>) Iso3166CountriesFetcher.fetchFromWikiHtml(HTML);
            Assert.assertTrue(fetchedCountries.size() == EXPECTED_RESULT_COUNTRY_COUNT);
            
            CountryIso3166 fetchedCountry = fetchedCountries.get(0);
            
            Assert.assertTrue(fetchedCountry.getCode().equals(EXPECTED_RESULT_COUNTRY.getCode()));
            Assert.assertTrue(fetchedCountry.getCountryCodeTopLevelDomain().equals(EXPECTED_RESULT_COUNTRY.getCountryCodeTopLevelDomain()));
            Assert.assertTrue(fetchedCountry.getName().equals(EXPECTED_RESULT_COUNTRY.getName()));
            Assert.assertTrue(fetchedCountry.getYearForStandardAssigned() == EXPECTED_RESULT_COUNTRY.getYearForStandardAssigned());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
