package softuni.exam.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferSeedRootDTO implements Serializable {

    @XmlElement(name = "offer")
    private List<OfferSeedDTO> offerSeedDTOS;

    public List<OfferSeedDTO> getOfferSeedDTOS() {
        return offerSeedDTOS;
    }

    public void setOfferSeedDTOS(List<OfferSeedDTO> offerSeedDTOS) {
        this.offerSeedDTOS = offerSeedDTOS;
    }
}
