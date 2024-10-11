package _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class UserSoldDTO implements Serializable {
    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private int age;

    @Expose
    private ProductSoldByUserDTO soldProduct;

    public UserSoldDTO() {
    }

    public UserSoldDTO(String firstName, String lastName, int age, ProductSoldByUserDTO soldProduct) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.soldProduct = soldProduct;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ProductSoldByUserDTO getSoldProduct() {
        return soldProduct;
    }

    public void setSoldProduct(ProductSoldByUserDTO soldProduct) {
        this.soldProduct = soldProduct;
    }
}
