package exam.model.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownSeedRootDTO implements Serializable {

    @XmlElement(name = "town")
    private List<TownSeedDTO> townSeedDTOS;

    public List<TownSeedDTO> getTownSeedDTOS() {
        return townSeedDTOS;
    }

    public void setTownSeedDTOS(List<TownSeedDTO> townSeedDTOS) {
        this.townSeedDTOS = townSeedDTOS;
    }
}
