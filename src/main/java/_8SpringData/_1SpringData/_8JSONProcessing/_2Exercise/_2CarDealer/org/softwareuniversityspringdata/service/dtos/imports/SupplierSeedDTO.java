package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.imports;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class SupplierSeedDTO implements Serializable {
    @Expose
    private String name;
    @Expose
    private boolean isImporter;

    public SupplierSeedDTO() {
    }

    public SupplierSeedDTO(String name, boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
