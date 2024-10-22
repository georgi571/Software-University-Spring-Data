package softuni.exam.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerSeedRootDTO implements Serializable {

    @XmlElement(name = "seller")
    private List<SellerSeedDTO> sellerSeedDTOS;

    public List<SellerSeedDTO> getSellerSeedDTOS() {
        return sellerSeedDTOS;
    }

    public void setSellerSeedDTOS(List<SellerSeedDTO> sellerSeedDTOS) {
        this.sellerSeedDTOS = sellerSeedDTOS;
    }
}
