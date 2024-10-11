package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.imports.PartSeedDTO;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.entities.Part;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.entities.Supplier;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.repositories.PartRepository;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.repositories.SupplierRepository;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.PartService;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PartServiceImpl implements PartService {
    private static final String FILE_PATH = "src/main/resources/json/imports/parts.json";
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedParts() throws FileNotFoundException {
        if (this.partRepository.count() == 0) {
            PartSeedDTO[] partSeedDTOs = this.gson.fromJson(new FileReader(FILE_PATH), PartSeedDTO[].class);
            for (PartSeedDTO partSeedDTO : partSeedDTOs) {
                if (!this.validationUtil.isValid(partSeedDTO)) {
                    this.validationUtil.getViolations(partSeedDTO)
                            .forEach(validationUtil -> System.out.printf("%s%n", validationUtil.getMessage()));
                    continue;
                }
                Part part = this.modelMapper.map(partSeedDTO, Part.class);
                part.setSupplier(getRandomSupplier());
                this.partRepository.saveAndFlush(part);
            }
        }
    }

    private Supplier getRandomSupplier() {
        return this.supplierRepository.findById(
                ThreadLocalRandom.current().nextLong(1, this.supplierRepository.count() + 1)
        ).get();
    }
}
