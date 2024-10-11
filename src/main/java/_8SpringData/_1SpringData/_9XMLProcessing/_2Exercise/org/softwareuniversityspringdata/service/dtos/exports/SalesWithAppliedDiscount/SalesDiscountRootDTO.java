package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.exports.SalesWithAppliedDiscount;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesDiscountRootDTO implements Serializable {

    @XmlElement(name = "sale")
    private List<SalesDiscountDTO> salesDiscountDTOList;

    public SalesDiscountRootDTO() {
    }

    public SalesDiscountRootDTO(List<SalesDiscountDTO> salesDiscountDTOList) {
        this.salesDiscountDTOList = salesDiscountDTOList;
    }

    public List<SalesDiscountDTO> getSalesDiscountDTOList() {
        return salesDiscountDTOList;
    }

    public void setSalesDiscountDTOList(List<SalesDiscountDTO> salesDiscountDTOList) {
        this.salesDiscountDTOList = salesDiscountDTOList;
    }
}
