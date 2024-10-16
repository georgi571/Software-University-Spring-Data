package softuni.exam.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "astronomers")
@XmlAccessorType(XmlAccessType.FIELD)
public class AstronomerSeedRootDTO implements Serializable {

    @XmlElement(name = "astronomer")
    private List<AstronomerSeedDTO> astronomerSeedDTOList;

    public List<AstronomerSeedDTO> getAstronomerSeedDTOList() {
        return astronomerSeedDTOList;
    }

    public void setAstronomerSeedDTOList(List<AstronomerSeedDTO> astronomerSeedDTOList) {
        this.astronomerSeedDTOList = astronomerSeedDTOList;
    }
}
