package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.entities.Supplier;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.repositories.SupplierRepository;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.SupplierService;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.exports.SupplierLocal.SupplierLocalDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.exports.SupplierLocal.SupplierLocalRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.imports.SupplierSeedDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.imports.SupplierSeedRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.util.ValidationUtil;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.util.XmlParser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private static final String FILE_INPUT_PATH = "src/main/resources/xml/imports/suppliers.xml";
    private static final String FILE_OUTPUT_PATH = "src/main/resources/xml/exports/local-suppliers.xml";

    private final SupplierRepository supplierRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedSupplier() throws JAXBException {
        if (this.supplierRepository.count() == 0) {
            SupplierSeedRootDTO supplierSeedRootDTO = xmlParser.parse(SupplierSeedRootDTO.class, FILE_INPUT_PATH);
            for (SupplierSeedDTO supplierSeedDTO : supplierSeedRootDTO.getSupplierSeedDTOList()) {
                if (!this.validationUtil.isValid(supplierSeedDTO)) {
                    this.validationUtil.getViolations(supplierSeedDTO)
                            .forEach(validationUtil -> System.out.printf("%s%n", validationUtil.getMessage()));
                    continue;
                }
                Supplier supplier = this.modelMapper.map(supplierSeedDTO, Supplier.class);
                this.supplierRepository.saveAndFlush(supplier);
            }
        }
    }

    @Override
    public void exportLocalSuppliers() throws JAXBException {
        List<SupplierLocalDTO> supplierLocalDTOList = this.supplierRepository.findAllByIsImporter(false)
                .stream()
                .map(supplier -> {
                    SupplierLocalDTO dto = this.modelMapper.map(supplier, SupplierLocalDTO.class);
                    dto.setPartsCount(supplier.getParts().size());
                    return dto;
                })
                .collect(Collectors.toList());

        SupplierLocalRootDTO supplierLocalRootDTO = new SupplierLocalRootDTO();
        supplierLocalRootDTO.setLocalDTOList(supplierLocalDTOList);

        this.xmlParser.exportToFile(SupplierLocalRootDTO.class, supplierLocalRootDTO, FILE_OUTPUT_PATH);
    }
}
