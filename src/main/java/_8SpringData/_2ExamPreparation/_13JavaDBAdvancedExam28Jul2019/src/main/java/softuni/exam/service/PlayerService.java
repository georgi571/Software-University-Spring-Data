package softuni.exam.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PlayerService {
    String importPlayers() throws IOException;

    boolean areImported();

    String readPlayersJsonFile() throws FileNotFoundException;

    String exportPlayersWhereSalaryBiggerThan();

    String exportPlayersInATeam();
}
