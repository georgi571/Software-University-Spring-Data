package _8SpringData._1SpringData._8JSONProcessing._1Lab._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserSoldProductsDTO implements Serializable {
    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private List<ProductInfoDTO.ProductSoldDTO> soldProducts;

    public UserSoldProductsDTO() {
        this.soldProducts = new ArrayList<>();
    }

    public UserSoldProductsDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.soldProducts = new ArrayList<>();

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

    public List<ProductInfoDTO.ProductSoldDTO> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductInfoDTO.ProductSoldDTO> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
