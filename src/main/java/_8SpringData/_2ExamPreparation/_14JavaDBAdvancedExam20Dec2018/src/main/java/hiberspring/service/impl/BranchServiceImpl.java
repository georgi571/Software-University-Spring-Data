package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.json.BranchSeedDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import hiberspring.service.BranchService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {

    private final String PATH_INPUT = "src/main/resources/files/branches.json";

    private final BranchRepository branchRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;

    public BranchServiceImpl(BranchRepository branchRepository, TownRepository townRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil) {
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return this.fileUtil.readFile(PATH_INPUT);
    }

    @Override
    public String importBranches(String branchesFileContent) throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();

        BranchSeedDTO[] branchSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), BranchSeedDTO[].class);
        for (BranchSeedDTO branchSeedDTO : branchSeedDTOS) {
            Optional<Branch> branchOptional = this.branchRepository.findByName(branchSeedDTO.getName());
            Optional<Town> townOptional = this.townRepository.findByName(branchSeedDTO.getTown());
            if (!this.validationUtil.isValid(branchSeedDTO) || branchOptional.isPresent() || townOptional.isEmpty()) {
                stringBuilder.append("Error: Invalid data.").append(System.lineSeparator());
                continue;
            }
            Branch branch = this.modelMapper.map(branchSeedDTO, Branch.class);
            branch.setTown(townOptional.get());
            this.branchRepository.saveAndFlush(branch);
            stringBuilder.append(String.format("Successfully imported Branch %s.", branch.getName())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
