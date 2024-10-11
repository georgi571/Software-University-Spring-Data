package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.imports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartSeedRootDTO implements Serializable {

    @XmlElement(name = "part")
    private List<PartSeedDTO> partSeedDTOList;

    public PartSeedRootDTO() {
    }

    public PartSeedRootDTO(List<PartSeedDTO> partSeedDTOList) {
        this.partSeedDTOList = partSeedDTOList;
    }

    public List<PartSeedDTO> getPartSeedDTOList() {
        return partSeedDTOList;
    }

    public void setPartSeedDTOList(List<PartSeedDTO> partSeedDTOList) {
        this.partSeedDTOList = partSeedDTOList;
    }
}
