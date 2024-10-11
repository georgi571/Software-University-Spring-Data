package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CustomerOrder;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class SalesOrderedDTO implements Serializable {

    @Expose
    private CarOrderedDTO car;

    @Expose
    private double discount;

    @Expose
    private BigDecimal price;

    @Expose
    private BigDecimal priceWithDiscount;

    public SalesOrderedDTO() {
    }

    public SalesOrderedDTO(CarOrderedDTO car, double discount, BigDecimal price, BigDecimal priceWithDiscount) {
        this.car = car;
        this.discount = discount;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
    }

    public CarOrderedDTO getCarOrderedDTO() {
        return car;
    }

    public void setCarOrderedDTO(CarOrderedDTO carOrderedDTO) {
        this.car = carOrderedDTO;
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
