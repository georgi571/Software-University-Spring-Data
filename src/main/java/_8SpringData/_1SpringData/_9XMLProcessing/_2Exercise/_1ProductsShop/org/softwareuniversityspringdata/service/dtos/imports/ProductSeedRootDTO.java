package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.imports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedRootDTO implements Serializable {

    @XmlElement(name = "product")
    private List<ProductSeedDTO> productSeedDTOList;

    public ProductSeedRootDTO() {
    }

    public ProductSeedRootDTO(List<ProductSeedDTO> productSeedDTOList) {
        this.productSeedDTOList = productSeedDTOList;
    }

    public List<ProductSeedDTO> getProductSeedDTOList() {
        return productSeedDTOList;
    }

    public void setProductSeedDTOList(List<ProductSeedDTO> productSeedDTOList) {
        this.productSeedDTOList = productSeedDTOList;
    }
}
