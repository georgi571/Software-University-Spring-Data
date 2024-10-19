package softuni.exam.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "apartments")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentSeedRootDTO implements Serializable {

    @XmlElement(name = "apartment")
    private List<ApartmentSeedDTO> apartmentSeedDTOS;

    public List<ApartmentSeedDTO> getApartmentSeedDTOS() {
        return apartmentSeedDTOS;
    }

    public void setApartmentSeedDTOS(List<ApartmentSeedDTO> apartmentSeedDTOS) {
        this.apartmentSeedDTOS = apartmentSeedDTOS;
    }
}
