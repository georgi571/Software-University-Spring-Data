package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.xml.TeamSeedDTO;
import softuni.exam.domain.dtos.xml.TeamSeedRootDTO;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    private final String PATH_INPUT = "src/main/resources/files/xml/teams.xml";

    private final TeamRepository teamRepository;
    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validationUtil;
    private final FileUtil fileUtil;

    public TeamServiceImpl(TeamRepository teamRepository, PictureRepository pictureRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidatorUtil validationUtil, FileUtil fileUtil) {
        this.teamRepository = teamRepository;
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public String importTeams() throws IOException {
        return this.fileUtil.readFile(PATH_INPUT);
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsXmlFile() throws JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        TeamSeedRootDTO teamSeedRootDTO = this.xmlParser.parse(TeamSeedRootDTO.class, PATH_INPUT);
        for (TeamSeedDTO teamSeedDTO : teamSeedRootDTO.getTeamSeedDTOList()) {
            Optional<Team> teamOptional = this.teamRepository.findByName(teamSeedDTO.getName());
            Optional<Picture> pictureOptional = this.pictureRepository.findByUrl(teamSeedDTO.getPicture().getUrl());
            if (!this.validationUtil.isValid(teamSeedDTO) || teamOptional.isPresent() || pictureOptional.isEmpty()) {
                stringBuilder.append("Invalid team").append(System.lineSeparator());
                continue;
            }
            Team team = this.modelMapper.map(teamSeedDTO, Team.class);
            team.setPicture(pictureOptional.get());
            this.teamRepository.saveAndFlush(team);
            stringBuilder.append(String.format("Successfully imported - %s", team.getName())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
