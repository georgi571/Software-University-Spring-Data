package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.imports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSeedRootDTO implements Serializable {

    @XmlElement(name = "user")
    private List<UserSeedDTO> userSeedDTOList;

    public UserSeedRootDTO() {
    }

    public UserSeedRootDTO(List<UserSeedDTO> userSeedDTOList) {
        this.userSeedDTOList = userSeedDTOList;
    }

    public List<UserSeedDTO> getUserSeedDTOList() {
        return userSeedDTOList;
    }

    public void setUserSeedDTOList(List<UserSeedDTO> userSeedDTOList) {
        this.userSeedDTOList = userSeedDTOList;
    }
}
