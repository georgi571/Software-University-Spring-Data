package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.Town;

import java.io.IOException;

public interface TownService {
    void seedTowns() throws IOException;

    Town getTownByName(String name);

    void createTown(Town town);
}
