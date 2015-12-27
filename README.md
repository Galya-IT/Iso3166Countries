# Iso3166Countries

The application fetches from wikipedia ISO 3166-1 alpha-2 codes (two-letter country codes to represent countries, dependent territories, and special areas of geographical interest), saves them in database and prints them in the console.

Technologies and libraries used:
	Maven for library dependencies;
	Apache HttpClient for fetching data on the web;
	JSoup for HTML parsing;
	JDBC, JPA and Hibernate to connect with in-memory DB - HyperSQL DataBase;
	JUnit.

The project is built and run under Java 1.8.0_31.
Build the project in command line: mvn exec:java

Eclipse project files included .