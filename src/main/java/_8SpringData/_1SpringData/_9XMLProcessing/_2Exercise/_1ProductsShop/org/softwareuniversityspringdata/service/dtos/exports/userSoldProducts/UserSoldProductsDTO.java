package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.userSoldProducts;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoldProductsDTO implements Serializable {
    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElement(name = "sold-products")
    private ProductInfoRootDTO productInfoRootDTO;

    public UserSoldProductsDTO() {

    }

    public UserSoldProductsDTO(String firstName, String lastName, ProductInfoRootDTO productInfoRootDTO) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.productInfoRootDTO = productInfoRootDTO;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ProductInfoRootDTO getProductInfoRootDTO() {
        return productInfoRootDTO;
    }

    public void setProductInfoRootDTO(ProductInfoRootDTO productInfoRootDTO) {
        this.productInfoRootDTO = productInfoRootDTO;
    }
}
