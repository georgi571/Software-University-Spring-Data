package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.SalesWithAppliedDiscount;

import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CarAndParts.CarDTO;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class SalesDiscountDTO implements Serializable {

    @Expose
    private CarDTO car;

    @Expose
    private String customerName;

    @Expose
    private double discount;

    @Expose
    private BigDecimal price;

    @Expose
    private BigDecimal priceWithDiscount;

    public SalesDiscountDTO() {
    }

    public SalesDiscountDTO(CarDTO carDTO, String customerName, double discount, BigDecimal price, BigDecimal priceWithDiscount) {
        this.car = carDTO;
        this.customerName = customerName;
        this.discount = discount;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
    }

    public CarDTO getCarDTO() {
        return car;
    }

    public void setCarDTO(CarDTO carDTO) {
        this.car = carDTO;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
