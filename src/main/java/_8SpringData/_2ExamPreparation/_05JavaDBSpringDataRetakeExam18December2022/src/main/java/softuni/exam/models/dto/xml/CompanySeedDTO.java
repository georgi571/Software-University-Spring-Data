package softuni.exam.models.dto.xml;

import softuni.exam.util.adapters.LocalDateTimeAdapterXML;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanySeedDTO implements Serializable {

    @XmlElement(name = "companyName")
    @Size(min = 2, max = 40)
    private String name;

    @XmlElement(name = "dateEstablished")
    @XmlJavaTypeAdapter(LocalDateTimeAdapterXML.class)
    private LocalDate dateEstablished;

    @XmlElement(name = "website")
    @Size(min = 2, max = 30)
    private String website;

    @XmlElement(name = "countryId")
    private Long countryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateEstablished() {
        return dateEstablished;
    }

    public void setDateEstablished(LocalDate dateEstablished) {
        this.dateEstablished = dateEstablished;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
