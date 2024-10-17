package softuni.exam.models.dto.xml;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "job")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobSeedDTO implements Serializable {

    @XmlElement(name = "jobTitle")
    @Size(min = 2, max = 40)
    private String title;

    @XmlElement(name = "hoursAWeek")
    @DecimalMin(value = "10.00")
    private double hoursAWeek;

    @XmlElement(name = "salary")
    @DecimalMin(value = "300.00")
    private double salary;

    @XmlElement(name = "description")
    @Size(min = 5)
    private String description;

    @XmlElement(name = "companyId")
    private Long companyId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getHoursAWeek() {
        return hoursAWeek;
    }

    public void setHoursAWeek(double hoursAWeek) {
        this.hoursAWeek = hoursAWeek;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
