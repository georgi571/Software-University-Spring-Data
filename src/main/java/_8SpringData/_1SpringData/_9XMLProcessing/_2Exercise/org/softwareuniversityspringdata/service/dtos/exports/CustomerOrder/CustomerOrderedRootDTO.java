package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.exports.CustomerOrder;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerOrderedRootDTO implements Serializable {

    @XmlElement(name = "customer")
    private List<CustomerOrderedDTO> customerOrderedDTOList;

    public CustomerOrderedRootDTO() {
    }

    public CustomerOrderedRootDTO(List<CustomerOrderedDTO> customerOrderedDTOList) {
        this.customerOrderedDTOList = customerOrderedDTOList;
    }

    public List<CustomerOrderedDTO> getCustomerOrderedDTOList() {
        return customerOrderedDTOList;
    }

    public void setCustomerOrderedDTOList(List<CustomerOrderedDTO> customerOrderedDTOList) {
        this.customerOrderedDTOList = customerOrderedDTOList;
    }
}
