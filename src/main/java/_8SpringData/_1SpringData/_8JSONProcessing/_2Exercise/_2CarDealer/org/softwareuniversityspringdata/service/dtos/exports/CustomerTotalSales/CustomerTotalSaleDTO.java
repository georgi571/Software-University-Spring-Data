package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CustomerTotalSales;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class CustomerTotalSaleDTO implements Serializable {

    @Expose
    private String fullName;

    @Expose
    private int boughtCars;

    @Expose
    private BigDecimal spendMoney;

    public CustomerTotalSaleDTO() {
    }

    public CustomerTotalSaleDTO(String fullName, int boughtCars, BigDecimal spendMoney) {
        this.fullName = fullName;
        this.boughtCars = boughtCars;
        this.spendMoney = spendMoney;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(int boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpendMoney() {
        return spendMoney;
    }

    public void setSpendMoney(BigDecimal spendMoney) {
        this.spendMoney = spendMoney;
    }
}
