package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos.GameAddDTO;

import java.util.Map;

public interface GameService {
    String addGame(GameAddDTO gameAddDTO);

    String editGame(long id, Map<String, String> map);

    String deleteGame(long id);

    String getAllGames();

    String getDetailsForGameWithGivenTitle(String title);

    String getOwnedGames();
}
