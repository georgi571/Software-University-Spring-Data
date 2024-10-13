package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.productsInRange;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductInRangeRootDTO implements Serializable {

    @XmlElement(name = "product")
    private List<ProductInRangeDTO> productInRangeDTOList;

    public ProductInRangeRootDTO() {
    }

    public ProductInRangeRootDTO(List<ProductInRangeDTO> productInRangeDTOList) {
        this.productInRangeDTOList = productInRangeDTOList;
    }

    public List<ProductInRangeDTO> getProductInRangeDTOList() {
        return productInRangeDTOList;
    }

    public void setProductInRangeDTOList(List<ProductInRangeDTO> productInRangeDTOList) {
        this.productInRangeDTOList = productInRangeDTOList;
    }
}
