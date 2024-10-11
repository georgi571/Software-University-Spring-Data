package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.exports.SupplierLocal;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierLocalRootDTO implements Serializable {

    @XmlElement(name = "supplier")
    private List<SupplierLocalDTO> localDTOList;

    public SupplierLocalRootDTO() {
    }

    public SupplierLocalRootDTO(List<SupplierLocalDTO> localDTOList) {
        this.localDTOList = localDTOList;
    }

    public List<SupplierLocalDTO> getLocalDTOList() {
        return localDTOList;
    }

    public void setLocalDTOList(List<SupplierLocalDTO> localDTOList) {
        this.localDTOList = localDTOList;
    }
}
