package exam.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "shops")
public class Shop extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "income", nullable = false)
    private BigDecimal income;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "employee_count", nullable = false)
    private int employeeCount;

    @Column(name = "shop_area", nullable = false)
    private int shopArea;

    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town town;

    @OneToMany(mappedBy = "shop")
    private Set<Laptop> laptops;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

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

    public int getShopArea() {
        return shopArea;
    }

    public void setShopArea(int shopArea) {
        this.shopArea = shopArea;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Set<Laptop> getLaptops() {
        return laptops;
    }

    public void setLaptops(Set<Laptop> laptops) {
        this.laptops = laptops;
    }
}
