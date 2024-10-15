package softuni.exam.models.dto.xmls;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "device")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceSeedDTO implements Serializable {

    @XmlElement(name = "brand")
    @Size(min = 2, max = 20)
    private String brand;

    @XmlElement(name = "device_type")
    private String deviceType;

    @XmlElement(name = "model")
    @Size(min = 1, max = 20)
    private String model;

    @XmlElement(name = "price")
    private double price;

    @XmlElement(name = "storage")
    private int storage;

    @XmlElement(name = "sale_id")
    private Long sale_id;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
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

    public Long getSale_id() {
        return sale_id;
    }

    public void setSale_id(Long sale_id) {
        this.sale_id = sale_id;
    }
}
