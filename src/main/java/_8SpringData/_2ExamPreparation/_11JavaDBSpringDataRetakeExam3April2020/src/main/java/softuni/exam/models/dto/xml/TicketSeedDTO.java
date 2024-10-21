package softuni.exam.models.dto.xml;

import softuni.exam.util.adapters.LocalDateTimeAdapterXML;

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

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketSeedDTO implements Serializable {

    @XmlElement(name = "serial-number")
    @Size(min = 2)
    private String serialNumber;

    @XmlElement(name = "price")
    @Positive
    private BigDecimal price;

    @XmlElement(name = "take-off")
    @XmlJavaTypeAdapter(LocalDateTimeAdapterXML.class)
    private LocalDateTime takeOff;

    @XmlElement(name = "from-town")
    private FromTownDTO fromTown;

    @XmlElement(name = "to-town")
    private ToTownSeedDTO toTown;

    @XmlElement(name = "passenger")
    private PassengerSeedDTO passenger;

    @XmlElement(name = "plane")
    private PlaneSeedForTicketDTO plane;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getTakeOff() {
        return takeOff;
    }

    public void setTakeOff(LocalDateTime takeOff) {
        this.takeOff = takeOff;
    }

    public FromTownDTO getFromTown() {
        return fromTown;
    }

    public void setFromTown(FromTownDTO fromTown) {
        this.fromTown = fromTown;
    }

    public ToTownSeedDTO getToTown() {
        return toTown;
    }

    public void setToTown(ToTownSeedDTO toTown) {
        this.toTown = toTown;
    }

    public PassengerSeedDTO getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerSeedDTO passenger) {
        this.passenger = passenger;
    }

    public PlaneSeedForTicketDTO getPlane() {
        return plane;
    }

    public void setPlane(PlaneSeedForTicketDTO plane) {
        this.plane = plane;
    }
}
