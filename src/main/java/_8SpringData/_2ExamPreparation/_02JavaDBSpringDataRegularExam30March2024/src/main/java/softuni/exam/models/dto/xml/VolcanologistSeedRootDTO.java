package softuni.exam.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "volcanologists")
@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistSeedRootDTO implements Serializable {

    @XmlElement(name = "volcanologist")
    private List<VolcanologistSeedDTO> volcanologistSeedDTOList;

    public List<VolcanologistSeedDTO> getVolcanologistSeedDTOList() {
        return volcanologistSeedDTOList;
    }

    public void setVolcanologistSeedDTOList(List<VolcanologistSeedDTO> volcanologistSeedDTOList) {
        this.volcanologistSeedDTOList = volcanologistSeedDTOList;
    }
}
