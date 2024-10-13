package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.userAndProducts;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsRootDTO implements Serializable {

    @XmlAttribute(name = "count")
    private int count;

    @XmlElement(name = "product")
    private List<SoldProductDTO> soldProductDTOList;

    public SoldProductsRootDTO() {
    }

    public SoldProductsRootDTO(int count, List<SoldProductDTO> soldProductDTOList) {
        this.count = count;
        this.soldProductDTOList = soldProductDTOList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<SoldProductDTO> getSoldProductDTOList() {
        return soldProductDTOList;
    }

    public void setSoldProductDTOList(List<SoldProductDTO> soldProductDTOList) {
        this.soldProductDTOList = soldProductDTOList;
    }
}
