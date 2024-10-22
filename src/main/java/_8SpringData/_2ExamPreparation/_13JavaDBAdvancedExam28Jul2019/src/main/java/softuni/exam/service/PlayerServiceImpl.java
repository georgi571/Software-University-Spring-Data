package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.json.PlayerSeedDTO;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.Postion;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final String PATH_INPUT = "src/main/resources/files/json/players.json";

    private final PlayerRepository playerRepository;
    private final PictureRepository pictureRepository;
    private final TeamRepository teamRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validationUtil;
    private final FileUtil fileUtil;

    public PlayerServiceImpl(PlayerRepository playerRepository, PictureRepository pictureRepository, TeamRepository teamRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidatorUtil validationUtil, FileUtil fileUtil) {
        this.playerRepository = playerRepository;
        this.pictureRepository = pictureRepository;
        this.teamRepository = teamRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public String importPlayers() throws IOException {
        return this.fileUtil.readFile(PATH_INPUT);
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();

        PlayerSeedDTO[] playerSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), PlayerSeedDTO[].class);
        for (PlayerSeedDTO playerSeedDTO : playerSeedDTOS) {
            Optional<Picture> teamPictureOptional = this.pictureRepository.findByUrl(playerSeedDTO.getTeam().getPicture().getUrl());
            Optional<Team> teamOptional = this.teamRepository.findByNameAndPicture(playerSeedDTO.getTeam().getName(), teamPictureOptional.get());
            Optional<Picture> pictureOptional = this.pictureRepository.findByUrl(playerSeedDTO.getPicture().getUrl());
            Postion position;
            try {
                position = Postion.valueOf(playerSeedDTO.getPosition());
            } catch (IllegalArgumentException e) {
                position = null;
            }
            if (!this.validationUtil.isValid(playerSeedDTO) || teamOptional.isEmpty() || pictureOptional.isEmpty() || position == null) {
                stringBuilder.append("Invalid player").append(System.lineSeparator());
                continue;
            }
            Player player = this.modelMapper.map(playerSeedDTO, Player.class);
            Picture picture = pictureOptional.get();
            Team team = teamOptional.get();
            player.setPosition(position);
            player.setPicture(picture);
            player.setTeam(team);
            this.playerRepository.saveAndFlush(player);
            stringBuilder.append(String.format("Successfully imported player: %s %s", player.getFirstName(), player.getLastName())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        return this.playerRepository.findAllBySalaryGreaterThanOrderBySalaryDesc(BigDecimal.valueOf(100000))
                .stream()
                .map(player -> String.format("Player name: %s %s \n" +
                                "         Number: %d\n" +
                                "         Salary: %.2f\n" +
                                "         Team: %s\n",
                        player.getFirstName(), player.getLastName(),
                        player.getNumber(),
                        player.getSalary(),
                        player.getTeam().getName()))
                .collect(Collectors.joining());
    }

    @Override
    public String exportPlayersInATeam() {
        Team team = this.teamRepository.findByName("North Hub").get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Team: %s", team.getName())).append(System.lineSeparator());
        this.playerRepository.findAllByTeamOrderById(team)
                .forEach(player -> stringBuilder.append(String.format("             Player name: %s %s - %s\n" +
                                "             Number: %d",
                        player.getFirstName(),
                        player.getLastName(),
                        player.getPosition(),
                        player.getNumber())).append(System.lineSeparator()));
        return stringBuilder.toString().trim();
    }
}
