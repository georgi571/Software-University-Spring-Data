package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.imports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedRootDTO implements Serializable {

    @XmlElement(name = "category")
    private List<CategorySeedDTO> categorySeedDTOList;

    public CategorySeedRootDTO() {
    }

    public CategorySeedRootDTO(List<CategorySeedDTO> categorySeedDTOList) {
        this.categorySeedDTOList = categorySeedDTOList;
    }

    public List<CategorySeedDTO> getCategorySeedDTOList() {
        return categorySeedDTOList;
    }

    public void setCategorySeedDTOList(List<CategorySeedDTO> categorySeedDTOList) {
        this.categorySeedDTOList = categorySeedDTOList;
    }
}
