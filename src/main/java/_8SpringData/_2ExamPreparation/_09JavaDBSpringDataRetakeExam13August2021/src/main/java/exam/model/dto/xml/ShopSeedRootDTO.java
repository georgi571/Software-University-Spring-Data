package exam.model.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "shops")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopSeedRootDTO implements Serializable {

    @XmlElement(name = "shop")
    private List<ShopSeedDTO> shopSeedDTOList;

    public List<ShopSeedDTO> getShopSeedDTOList() {
        return shopSeedDTOList;
    }

    public void setShopSeedDTOList(List<ShopSeedDTO> shopSeedDTOList) {
        this.shopSeedDTOList = shopSeedDTOList;
    }
}
