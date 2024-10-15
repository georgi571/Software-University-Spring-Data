package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "devices")
public class Device extends BaseEntity {

    @Column(name = "brand", nullable = false)
    private String brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_type")
    private DeviceType deviceType;

    @Column(name = "model", nullable = false, unique = true)
    private String model;

    @Column(name = "price")
    private double price;

    @Column(name = "storage")
    private int storage;

    @ManyToOne
    @JoinColumn(name = "sale_id", referencedColumnName = "id")
    private Sale sale;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }


}
