package softuni.exam.models.dto.xml;

import softuni.exam.util.adapters.LocalDateTimeAdapterXML;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferSeedDTO implements Serializable {

    @XmlElement(name = "description")
    @Size(min = 5)
    private String description;

    @XmlElement(name = "price")
    @Positive
    private BigDecimal price;

    @XmlElement(name = "added-on")
    @XmlJavaTypeAdapter(LocalDateTimeAdapterXML.class)
    @NotNull
    private LocalDateTime addedOn;

    @XmlElement(name = "has-gold-status")
    private boolean hasGoldStatus;

    @XmlElement(name = "car")
    private CarDTO car;


    @XmlElement(name = "seller")
    private SellerDTO seller;

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

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public boolean isHasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public SellerDTO getSeller() {
        return seller;
    }

    public void setSeller(SellerDTO seller) {
        this.seller = seller;
    }
}
