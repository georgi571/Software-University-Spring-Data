package exam.model.dto.xml;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopSeedDTO implements Serializable {

    @XmlElement(name = "address")
    @Size(min = 4)
    private String address;

    @XmlElement(name = "employee-count")
    @Min(1)
    @Max(50)
    private int employeeCount;

    @XmlElement(name = "income")
    @DecimalMin(value = "20000")
    private BigDecimal income;

    @XmlElement(name = "name")
    @Size(min = 4)
    private String name;

    @XmlElement(name = "shop-area")
    @Min(150)
    private int shopArea;

    @XmlElement(name = "town")
    private TownShopDTO townShopDTO;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShopArea() {
        return shopArea;
    }

    public void setShopArea(int shopArea) {
        this.shopArea = shopArea;
    }

    public TownShopDTO getTownShopDTO() {
        return townShopDTO;
    }

    public void setTownShopDTO(TownShopDTO townShopDTO) {
        this.townShopDTO = townShopDTO;
    }
}
