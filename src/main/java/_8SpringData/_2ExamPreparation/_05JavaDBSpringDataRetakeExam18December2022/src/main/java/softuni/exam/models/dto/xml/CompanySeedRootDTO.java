package softuni.exam.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanySeedRootDTO implements Serializable {

    @XmlElement(name = "company")
    private List<CompanySeedDTO> companySeedDTOList;

    public List<CompanySeedDTO> getCompanySeedDTOList() {
        return companySeedDTOList;
    }

    public void setCompanySeedDTOList(List<CompanySeedDTO> companySeedDTOList) {
        this.companySeedDTOList = companySeedDTOList;
    }
}
