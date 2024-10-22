package softuni.exam.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "teams")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamSeedRootDTO implements Serializable {

    @XmlElement(name = "team")
    private List<TeamSeedDTO> teamSeedDTOList;

    public List<TeamSeedDTO> getTeamSeedDTOList() {
        return teamSeedDTOList;
    }

    public void setTeamSeedDTOList(List<TeamSeedDTO> teamSeedDTOList) {
        this.teamSeedDTOList = teamSeedDTOList;
    }
}
