package softuni.exam.models.dto.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "personal_datas")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonalDataSeedRootDTO implements Serializable {

    @XmlElement(name = "personal_data")
    private List<PersonalDataSeedDTO> personalDataSeedDTOs;

    public List<PersonalDataSeedDTO> getPersonalDataSeedDTOs() {
        return personalDataSeedDTOs;
    }

    public void setPersonalDataSeedDTOs(List<PersonalDataSeedDTO> personalDataSeedDTOs) {
        this.personalDataSeedDTOs = personalDataSeedDTOs;
    }
}
