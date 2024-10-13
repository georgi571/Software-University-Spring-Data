package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.catergoryByProducts;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryByProductRootDTO implements Serializable {

    @XmlElement(name = "category")
    private List<CategoryByProductsDTO> categoryByProductsDTOList;

    public CategoryByProductRootDTO() {
    }

    public CategoryByProductRootDTO(List<CategoryByProductsDTO> categoryByProductsDTOList) {
        this.categoryByProductsDTOList = categoryByProductsDTOList;
    }

    public List<CategoryByProductsDTO> getCategoryByProductsDTOList() {
        return categoryByProductsDTOList;
    }

    public void setCategoryByProductsDTOList(List<CategoryByProductsDTO> categoryByProductsDTOList) {
        this.categoryByProductsDTOList = categoryByProductsDTOList;
    }
}
