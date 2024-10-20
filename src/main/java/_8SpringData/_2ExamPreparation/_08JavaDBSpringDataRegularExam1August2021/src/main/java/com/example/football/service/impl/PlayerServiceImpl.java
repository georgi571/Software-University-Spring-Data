package com.example.football.service.impl;

import com.example.football.models.dto.xml.PlayersSeedDTO;
import com.example.football.models.dto.xml.PlayersSeedRootDTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Position;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final String PATH_INPUT = "src/main/resources/files/xml/players.xml";

    private final PlayerRepository playerRepository;
    private final TownRepository townRepository;
    private final TeamRepository teamRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public PlayerServiceImpl(PlayerRepository playerRepository, TownRepository townRepository, TeamRepository teamRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.playerRepository = playerRepository;
        this.townRepository = townRepository;
        this.teamRepository = teamRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importPlayers() throws JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        PlayersSeedRootDTO playersSeedRootDTO = this.xmlParser.parse(PlayersSeedRootDTO.class, PATH_INPUT);
        for (PlayersSeedDTO playerSeedDTO : playersSeedRootDTO.getPlayersSeedDTOList()) {
            Optional<Player> playerOptional = this.playerRepository.findByEmail(playerSeedDTO.getEmail());
            if (!this.validationUtil.isValid(playerSeedDTO) || playerOptional.isPresent()) {
                stringBuilder.append("Invalid player").append(System.lineSeparator());
                continue;
            }
            Player player = this.modelMapper.map(playerSeedDTO, Player.class);

            Position position = Position.valueOf(playerSeedDTO.getPosition());
            Town town = this.townRepository.findByName(playerSeedDTO.getTown().getName()).get();
            Team team = this.teamRepository.findByName(playerSeedDTO.getTeam().getName()).get();
            player.setPosition(position);
            player.setTown(town);
            player.setTeam(team);
            this.playerRepository.saveAndFlush(player);
            stringBuilder.append(String.format("Successfully imported Player %s %s - %s", player.getFirstName(), player.getLastName(), player.getPosition())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportBestPlayers() {
        return this.playerRepository.findAllByBirthDateBetweenOrderByStat(LocalDate.of(1995, 01, 01), LocalDate.of(2003, 01,01))
                .stream()
                .map(player -> String.format("Player - %s %s\n" +
                                " Position - %s\n" +
                                " Team - %s\n" +
                                " Stadium - %s\n",
                        player.getFirstName(), player.getLastName(),
                        player.getPosition(),
                        player.getTeam().getName(),
                        player.getTeam().getStadiumName()))
                .collect(Collectors.joining());
    }
}
