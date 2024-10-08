package _8SpringData._1SpringData._8JSONProcessing._1Lab._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class ProductSoldByUserDTO implements Serializable {

    @Expose
    private int count;

    @Expose
    private List<ProductInfoDTO> products;

    public ProductSoldByUserDTO() {
    }

    public ProductSoldByUserDTO(int count, List<ProductInfoDTO> products) {
        this.count = count;
        this.products = products;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductInfoDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInfoDTO> products) {
        this.products = products;
    }
}
