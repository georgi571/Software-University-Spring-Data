package _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CarAndParts;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartRootDTO implements Serializable {

    @XmlElement(name = "part")
    private List<PartDTO> partDTOList;

    public PartRootDTO() {
    }

    public PartRootDTO(List<PartDTO> partDTOList) {
        this.partDTOList = partDTOList;
    }

    public List<PartDTO> getPartDTOList() {
        return partDTOList;
    }

    public void setPartDTOList(List<PartDTO> partDTOList) {
        this.partDTOList = partDTOList;
    }
}
