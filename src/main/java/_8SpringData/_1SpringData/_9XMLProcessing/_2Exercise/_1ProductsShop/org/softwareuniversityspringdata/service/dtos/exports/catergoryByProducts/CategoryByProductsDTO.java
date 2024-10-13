package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.catergoryByProducts;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryByProductsDTO implements Serializable {
    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "products-count")
    private int productCount;

    @XmlElement(name = "average-price")
    private BigDecimal averagePrice;

    @XmlElement(name = "total-revenue")
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
