package softuni.exam.models.dto.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "devices")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceSeedRootDTO implements Serializable {

    @XmlElement(name = "device")
    private List<DeviceSeedDTO> seedDTOList;

    public List<DeviceSeedDTO> getSeedDTOList() {
        return seedDTOList;
    }

    public void setSeedDTOList(List<DeviceSeedDTO> seedDTOList) {
        this.seedDTOList = seedDTOList;
    }
}
