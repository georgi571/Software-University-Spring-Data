package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.imports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierSeedRootDTO implements Serializable {
    @XmlElement(name = "supplier")
    private List<SupplierSeedDTO> supplierSeedDTOList;

    public SupplierSeedRootDTO() {
    }

    public SupplierSeedRootDTO(List<SupplierSeedDTO> supplierSeedDTOList) {
        this.supplierSeedDTOList = supplierSeedDTOList;
    }

    public List<SupplierSeedDTO> getSupplierSeedDTOList() {
        return supplierSeedDTOList;
    }

    public void setSupplierSeedDTOList(List<SupplierSeedDTO> supplierSeedDTOList) {
        this.supplierSeedDTOList = supplierSeedDTOList;
    }
}
