package com.galya.iso3166countries;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.galya.iso3166countries.model.CountryIso3166;
import com.galya.iso3166countries.utils.CommonUtils;

public class Iso3166CountriesFetcher {

    private static final String WIKI_ISO_3166_COUNTRIES_PAGE_URL = "http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2";

    private static final int TABLE_COLUMNS_FOR_FETCHING = 4;
    private static final int COLUMN_POSITION_COUNTRY_CODE = 0;
    private static final int COLUMN_POSITION_COUNTRY_NAME = 1;
    private static final int COLUMN_POSITION_YEAR_STANDARD_ASSIGNED = 2;
    private static final int COLUMN_POSITION_COUNTRY_CODE_TOP_LEVEL_DOMAIN = 3;

    public static Collection<CountryIso3166> fetchFromWiki() throws ClientProtocolException, IOException {
        Collection<CountryIso3166> countries = new HashSet<CountryIso3166>();

        String wikiIso3166PageHtmlString = CommonUtils.getPageHtml(WIKI_ISO_3166_COUNTRIES_PAGE_URL);
        countries = fetchFromWikiHtml(wikiIso3166PageHtmlString);

        return countries;
    }

    public static Collection<CountryIso3166> fetchFromWikiHtml(String html) throws ClientProtocolException, IOException {
        Collection<CountryIso3166> countries = new HashSet<CountryIso3166>();

        Document wikiIso3166PageHtml = Jsoup.parse(html);

        Elements iso3166CountriesTableRows = wikiIso3166PageHtml.select("table.wikitable.sortable").first()
                .select("tbody tr:not(:first-child)");

        for (Element tableRow : iso3166CountriesTableRows) {
            Elements tableRowTableDatas = tableRow.select("td");

            String countryCode = null;
            String countryName = null;
            int year = 0;
            String topLevelDomain = null;

            for (int i = 0; i < TABLE_COLUMNS_FOR_FETCHING; i++) {
                Element currentTableData = tableRowTableDatas.get(i);

                switch (i) {
                    case COLUMN_POSITION_COUNTRY_CODE:
                        countryCode = extractCountryCode(currentTableData);
                        break;
                    case COLUMN_POSITION_COUNTRY_NAME:
                        countryName = extractCountryName(currentTableData);
                        break;
                    case COLUMN_POSITION_YEAR_STANDARD_ASSIGNED:
                        year = extractYearStandardAssigned(currentTableData);
                        break;
                    case COLUMN_POSITION_COUNTRY_CODE_TOP_LEVEL_DOMAIN:
                        topLevelDomain = extractCountryCodeTopLevelDomain(currentTableData);
                        break;
                }
            }

            try {
                CountryIso3166 countryIso3166 = new CountryIso3166(countryCode, countryName, year, topLevelDomain);
                countries.add(countryIso3166);
            } catch (Exception e) {
                // TODO: proper handling when country code is fetched as null
                e.printStackTrace();
            }
        }

        return countries;
    }

    private static String extractCountryCode(Element element) {
        String countryCode = element.select("tt").html();
        return countryCode;
    }

    private static String extractCountryName(Element element) {
        String countryName = element.select("a").html();
        return countryName;
    }

    private static int extractYearStandardAssigned(Element element) {
        String yearStandardsAssigned = element.html();
        return Integer.parseInt(yearStandardsAssigned);
    }

    private static String extractCountryCodeTopLevelDomain(Element element) {
        String countryCodeTopLevelDomain = element.select("a").html();
        return countryCodeTopLevelDomain;
    }
}
