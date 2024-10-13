package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.userAndProducts;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserAndProductsDTO implements Serializable {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute(name = "age")
    private Integer age;

    @XmlElement(name = "sold-products")
    private SoldProductsRootDTO soldProductsRootDTO;

    public UserAndProductsDTO() {
    }

    public UserAndProductsDTO(String firstName, String lastName, Integer age, SoldProductsRootDTO soldProductsRootDTO) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.soldProductsRootDTO = soldProductsRootDTO;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SoldProductsRootDTO getSoldProductsRootDTO() {
        return soldProductsRootDTO;
    }

    public void setSoldProductsRootDTO(SoldProductsRootDTO soldProductsRootDTO) {
        this.soldProductsRootDTO = soldProductsRootDTO;
    }
}
