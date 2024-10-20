package com.example.football.service.impl;

import com.example.football.models.dto.json.TeamSeedDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    private final String PATH_INPUT = "src/main/resources/files/json/teams.json";

    private final TeamRepository teamRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importTeams() throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();

        TeamSeedDTO[] teamSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), TeamSeedDTO[].class);
        for (TeamSeedDTO teamSeedDTO : teamSeedDTOS) {
            Optional<Team> teamOptional = this.teamRepository.findByName(teamSeedDTO.getName());
            if (!this.validationUtil.isValid(teamSeedDTO) || teamOptional.isPresent()) {
                stringBuilder.append("Invalid team").append(System.lineSeparator());
                continue;
            }
            Team team = this.modelMapper.map(teamSeedDTO, Team.class);
            Town town = this.townRepository.findByName(teamSeedDTO.getTownName()).get();
            team.setTown(town);
            this.teamRepository.saveAndFlush(team);
            stringBuilder.append(String.format("SSuccessfully imported Team %s - %d", team.getName(), team.getFanBase())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
