package softuni.exam.models.dto.xml;

import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.util.adapters.LocalDateTimeAdapterXML;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferSeedDTO implements Serializable {

    @XmlElement(name = "price")
    @Positive
    private double price;

    @XmlElement(name = "publishedOn")
    @XmlJavaTypeAdapter(LocalDateTimeAdapterXML.class)
    private LocalDate publishedOn;

    @XmlElement(name = "apartment")
    private ApartmentOfferSeedDTO apartment;

    @XmlElement(name = "agent")
    private AgentOfferSeedDTO agent;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDate publishedOn) {
        this.publishedOn = publishedOn;
    }

    public ApartmentOfferSeedDTO getApartment() {
        return apartment;
    }

    public void setApartment(ApartmentOfferSeedDTO apartment) {
        this.apartment = apartment;
    }

    public AgentOfferSeedDTO getAgent() {
        return agent;
    }

    public void setAgent(AgentOfferSeedDTO agent) {
        this.agent = agent;
    }
}
