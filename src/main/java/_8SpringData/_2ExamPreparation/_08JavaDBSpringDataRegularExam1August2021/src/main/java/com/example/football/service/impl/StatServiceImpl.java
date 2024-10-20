package com.example.football.service.impl;

import com.example.football.models.dto.xml.StatSeedDTO;
import com.example.football.models.dto.xml.StatsSeedRootDTO;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class StatServiceImpl implements StatService {
    private final String PATH_INPUT = "src/main/resources/files/xml/stats.xml";

    private final StatRepository statRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public StatServiceImpl(StatRepository statRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.statRepository = statRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importStats() throws JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        StatsSeedRootDTO statsSeedRootDTO = this.xmlParser.parse(StatsSeedRootDTO.class, PATH_INPUT);
        for (StatSeedDTO statSeedDTO : statsSeedRootDTO.getStatSeedDTOS()) {
            Optional<Stat> statOptional = this.statRepository.findByPassingAndShootingAndEndurance(statSeedDTO.getPassing(), statSeedDTO.getShooting(), statSeedDTO.getEndurance());
            if (!this.validationUtil.isValid(statSeedDTO) || statOptional.isPresent()) {
                stringBuilder.append("Invalid stat").append(System.lineSeparator());
                continue;
            }
            Stat stat = this.modelMapper.map(statSeedDTO, Stat.class);
            this.statRepository.saveAndFlush(stat);
            stringBuilder.append(String.format("Successfully imported Stat %.2f - %.2f - %.2f", stat.getShooting(), stat.getPassing(), stat.getEndurance())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
