package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.imports;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedDTO implements Serializable {
    @XmlElement(name = "name")
    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    public CategorySeedDTO() {
    }

    public CategorySeedDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
