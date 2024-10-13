package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.userSoldProducts;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoldProductsRootDTO implements Serializable {
    @XmlElement(name = "user")
    private List<UserSoldProductsDTO> userSoldProductsDTOS;

    public UserSoldProductsRootDTO() {
    }

    public UserSoldProductsRootDTO(List<UserSoldProductsDTO> userSoldProductsDTOS) {
        this.userSoldProductsDTOS = userSoldProductsDTOS;
    }

    public List<UserSoldProductsDTO> getUserSoldProductsDTOS() {
        return userSoldProductsDTOS;
    }

    public void setUserSoldProductsDTOS(List<UserSoldProductsDTO> userSoldProductsDTOS) {
        this.userSoldProductsDTOS = userSoldProductsDTOS;
    }
}
