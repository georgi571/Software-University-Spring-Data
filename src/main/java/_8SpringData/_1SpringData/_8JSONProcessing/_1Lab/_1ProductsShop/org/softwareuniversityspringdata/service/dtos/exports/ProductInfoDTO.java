package _8SpringData._1SpringData._8JSONProcessing._1Lab._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductInfoDTO implements Serializable {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    public ProductInfoDTO() {
    }

    public ProductInfoDTO(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
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

    public static class ProductSoldDTO implements Serializable {
        @Expose
        private String name;

        @Expose
        private BigDecimal price;

        @Expose
        private String buyerFirstName;

        @Expose
        private String buyerLastName;

        public ProductSoldDTO() {
        }

        public ProductSoldDTO(String name, BigDecimal price, String buyerFirstName, String buyerLastName) {
            this.name = name;
            this.price = price;
            this.buyerFirstName = buyerFirstName;
            this.buyerLastName = buyerLastName;
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

        public String getBuyerFirstName() {
            return buyerFirstName;
        }

        public void setBuyerFirstName(String buyerFirstName) {
            this.buyerFirstName = buyerFirstName;
        }

        public String getBuyerLastName() {
            return buyerLastName;
        }

        public void setBuyerLastName(String buyerLastName) {
            this.buyerLastName = buyerLastName;
        }
    }
}
