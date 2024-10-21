package softuni.exam.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "planes")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneSeedRootDTO implements Serializable {

    @XmlElement(name = "plane")
    private List<PlaneSeedDTO> planeSeedDTOS;

    public List<PlaneSeedDTO> getPlaneSeedDTOS() {
        return planeSeedDTOS;
    }

    public void setPlaneSeedDTOS(List<PlaneSeedDTO> planeSeedDTOS) {
        this.planeSeedDTOS = planeSeedDTOS;
    }
}
