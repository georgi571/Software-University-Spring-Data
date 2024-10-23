package hiberspring.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedRootDTO implements Serializable {

    @XmlElement(name = "product")
    private List<ProductSeedDTO> productSeedDTOList;

    public List<ProductSeedDTO> getProductSeedDTOList() {
        return productSeedDTOList;
    }

    public void setProductSeedDTOList(List<ProductSeedDTO> productSeedDTOList) {
        this.productSeedDTOList = productSeedDTOList;
    }
}
