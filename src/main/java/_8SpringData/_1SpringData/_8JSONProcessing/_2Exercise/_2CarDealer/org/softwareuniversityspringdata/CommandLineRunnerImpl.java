package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata;

import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.*;
import org.softwareuniversityspringdata.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    public CommandLineRunnerImpl(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.supplierService.seedSupplier();
        this.partService.seedParts();
        this.carService.seedCars();
        this.customerService.seedCustomers();
        this.saleService.seedSales();

        this.customerService.exportOrderedCustomers();
        this.carService.exportModelCars("Toyota");
        this.supplierService.exportLocalSuppliers();
        this.carService.exportCarAndParts();
        this.customerService.exportCustomersWithBoughtCars();
        this.saleService.exportSales();
    }
}
