package exam.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "laptops")
public class Laptop extends BaseEntity {

    @Column(name = "mac_address", nullable = false, unique = true)
    private String macAddress;

    @Column(name = "cpu_speed", nullable = false)
    private double cpuSpeed;

    @Column(name = "ram", nullable = false)
    private int ram;

    @Column(name = "storage", nullable = false)
    private int storage;

    @Column(name = "description", columnDefinition = "text", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "warranty_type", nullable = false)
    private WarrantyType warrantyType;

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public WarrantyType getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(WarrantyType warrantyType) {
        this.warrantyType = warrantyType;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
