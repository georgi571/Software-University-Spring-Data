package _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class CategoryByProductsDTO implements Serializable {
    @Expose
    private String name;

    @Expose
    private int productCount;

    @Expose
    private BigDecimal averagePrice;

    @Expose
    private BigDecimal totalRevenue;

    public CategoryByProductsDTO() {
    }

    public CategoryByProductsDTO(String name, int productCount, BigDecimal averagePrice, BigDecimal totalRevenue) {
        this.name = name;
        this.productCount = productCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
