package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.Country;

import java.io.IOException;

public interface CountryService {
    void seedCountries() throws IOException;

    void createCountry(Country country);

    Country getCountryName(String name);
}
