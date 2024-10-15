package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.SaleSeedDto;
import softuni.exam.models.entity.Sale;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SaleRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SaleService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {

    private static final String PATH_INPUT = "src/main/resources/files/json/sales.json";

    private final SaleRepository saleRepository;
    private final SellerRepository sellerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public SaleServiceImpl(SaleRepository saleRepository, SellerRepository sellerRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.saleRepository = saleRepository;
        this.sellerRepository = sellerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.saleRepository.count() > 0;
    }

    @Override
    public String readSalesFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importSales() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        SaleSeedDto[] saleSeedDtos = this.gson.fromJson(new FileReader(PATH_INPUT), SaleSeedDto[].class);
        for (SaleSeedDto saleSeedDto : saleSeedDtos) {
            Optional<Sale> saleOptional = this.saleRepository.findByNumber(saleSeedDto.getNumber());
            if (!this.validationUtil.isValid(saleSeedDto) || saleOptional.isPresent()) {
                stringBuilder.append("Invalid sale").append(System.lineSeparator());
                continue;
            }
            Sale sale = this.modelMapper.map(saleSeedDto, Sale.class);
            Optional<Seller> sellerOptional = this.sellerRepository.findById(saleSeedDto.getSeller());
            if (sellerOptional.isPresent()) {
                sale.setSeller(sellerOptional.get());
            }
            this.saleRepository.saveAndFlush(sale);
            stringBuilder.append(String.format("Successfully imported sale with number %s", sale.getNumber())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
