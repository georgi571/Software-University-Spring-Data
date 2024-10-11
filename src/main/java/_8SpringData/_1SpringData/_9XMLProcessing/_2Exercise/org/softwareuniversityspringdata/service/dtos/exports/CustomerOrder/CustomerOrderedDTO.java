package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.exports.CustomerOrder;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.util.adapters.LocalDateTimeAdapter;

import java.time.LocalDateTime;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerOrderedDTO {

    @XmlElement(name = "id")
    private long id;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime birthDate;

    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;

    public CustomerOrderedDTO() {
    }

    public CustomerOrderedDTO(long id, String name, LocalDateTime birthDate, boolean isYoungDriver) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
