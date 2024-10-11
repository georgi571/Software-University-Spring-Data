package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.imports;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class PartSeedDTO implements Serializable {

    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    @Expose
    private int quantity;

    public PartSeedDTO() {
    }

    public PartSeedDTO(String name, BigDecimal price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
