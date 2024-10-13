package _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.imports.PartSeedDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.imports.PartSeedRootDTO;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.entities.Part;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.entities.Supplier;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.repositories.PartRepository;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.repositories.SupplierRepository;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.PartService;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.util.ValidationUtil;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.util.XmlParser;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class PartServiceImpl implements PartService {
    private static final String FILE_PATH = "src/main/resources/xml/imports/parts.xml";
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedParts() throws JAXBException {
        if (this.partRepository.count() == 0) {
            PartSeedRootDTO partSeedRootDTO = xmlParser.parse(PartSeedRootDTO.class, FILE_PATH);
            for (PartSeedDTO partSeedDTO : partSeedRootDTO.getPartSeedDTOList()) {
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
