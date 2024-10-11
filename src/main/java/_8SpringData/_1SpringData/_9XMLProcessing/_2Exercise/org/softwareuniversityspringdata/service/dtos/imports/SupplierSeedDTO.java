package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.imports;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierSeedDTO implements Serializable {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "is-importer")
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
