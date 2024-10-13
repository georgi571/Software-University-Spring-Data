package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.userSoldProducts;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductInfoRootDTO implements Serializable {

    @XmlElement(name = "product")
    private List<ProductInfoDTO> productInfoDTOList;

    public ProductInfoRootDTO() {
    }

    public ProductInfoRootDTO(List<ProductInfoDTO> productInfoDTOList) {
        this.productInfoDTOList = productInfoDTOList;
    }

    public List<ProductInfoDTO> getProductInfoDTOList() {
        return productInfoDTOList;
    }

    public void setProductInfoDTOList(List<ProductInfoDTO> productInfoDTOList) {
        this.productInfoDTOList = productInfoDTOList;
    }
}
