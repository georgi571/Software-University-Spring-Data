package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.AgentSeedDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.repository.AgentRepository;
import softuni.exam.service.AgentService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class AgentServiceImpl implements AgentService {
    private final String PATH_INPUT = "src/main/resources/files/json/agents.json";

    private final AgentRepository agentRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public AgentServiceImpl(AgentRepository agentRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.agentRepository = agentRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importAgents() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        AgentSeedDTO[] agentSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), AgentSeedDTO[].class);
        for (AgentSeedDTO agentSeedDTO : agentSeedDTOS) {
            Optional<Agent> agentOptional = this.agentRepository.findByFirstName(agentSeedDTO.getFirstName());
            if (!this.validationUtil.isValid(agentSeedDTO) || agentOptional.isPresent()) {
                stringBuilder.append("Invalid agent").append(System.lineSeparator());
                continue;
            }
            Agent agent = this.modelMapper.map(agentSeedDTO, Agent.class);
            this.agentRepository.saveAndFlush(agent);
            stringBuilder.append(String.format("Successfully imported agent - %s %s", agent.getFirstName(), agent.getLastName())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
