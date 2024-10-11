package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.imports.SupplierSeedDTO;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.entities.Supplier;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.repositories.SupplierRepository;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.SupplierService;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.SupplierLocal.SupplierLocalDTO;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private static final String FILE_INPUT_PATH = "src/main/resources/json/imports/suppliers.json";
    private static final String FILE_OUTPUT_PATH = "src/main/resources/json/exports/local-suppliers.json";

    private final SupplierRepository supplierRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedSupplier() throws FileNotFoundException {
        if (this.supplierRepository.count() == 0) {
            SupplierSeedDTO[] supplierSeedDTOs = this.gson.fromJson(new FileReader(FILE_INPUT_PATH), SupplierSeedDTO[].class);
            for (SupplierSeedDTO supplierSeedDTO : supplierSeedDTOs) {
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

    public List<SupplierLocalDTO> getAllLocalSuppliers(boolean isLocalSupplier) {
        return this.supplierRepository.findAllByIsImporter(isLocalSupplier)
                .stream()
                .map(supplier -> {
                    SupplierLocalDTO dto = this.modelMapper.map(supplier, SupplierLocalDTO.class);
                    int count = supplier.getParts().size();
                    dto.setPartsCount(count);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void exportLocalSuppliers() throws IOException {
        String localSupplierDTO = this.gson.toJson(this.getAllLocalSuppliers(false));
        FileWriter writer = new FileWriter(FILE_OUTPUT_PATH);
        writer.write(localSupplierDTO);
        writer.flush();
    }
}
