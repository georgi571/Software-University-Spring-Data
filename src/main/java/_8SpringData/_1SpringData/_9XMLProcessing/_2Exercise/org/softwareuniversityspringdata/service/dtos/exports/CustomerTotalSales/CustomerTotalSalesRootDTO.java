package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.exports.CustomerTotalSales;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerTotalSalesRootDTO implements Serializable {

    @XmlElement(name = "customer")
    private List<CustomerTotalSaleDTO> customerTotalSaleDTOList;

    public CustomerTotalSalesRootDTO() {
    }

    public CustomerTotalSalesRootDTO(List<CustomerTotalSaleDTO> customerTotalSaleDTOList) {
        this.customerTotalSaleDTOList = customerTotalSaleDTOList;
    }

    public List<CustomerTotalSaleDTO> getCustomerTotalSaleDTOList() {
        return customerTotalSaleDTOList;
    }

    public void setCustomerTotalSaleDTOList(List<CustomerTotalSaleDTO> customerTotalSaleDTOList) {
        this.customerTotalSaleDTOList = customerTotalSaleDTOList;
    }
}
