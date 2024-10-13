package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.userAndProducts;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserAndProductsRootDTO implements Serializable {

    @XmlAttribute(name = "count")
    private int count;

    @XmlElement(name = "user")
    private List<UserAndProductsDTO> userAndProductsDTOList;

    public UserAndProductsRootDTO() {
    }

    public UserAndProductsRootDTO(int count, List<UserAndProductsDTO> userAndProductsDTOList) {
        this.count = count;
        this.userAndProductsDTOList = userAndProductsDTOList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserAndProductsDTO> getUserAndProductsDTOList() {
        return userAndProductsDTOList;
    }

    public void setUserAndProductsDTOList(List<UserAndProductsDTO> userAndProductsDTOList) {
        this.userAndProductsDTOList = userAndProductsDTOList;
    }
}
