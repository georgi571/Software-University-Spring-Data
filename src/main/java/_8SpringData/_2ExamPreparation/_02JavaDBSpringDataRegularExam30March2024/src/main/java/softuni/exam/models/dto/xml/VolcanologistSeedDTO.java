package softuni.exam.models.dto.xml;

import softuni.exam.util.adapters.LocalDateTimeAdapterXML;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement(name = "volcanologist")
@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistSeedDTO implements Serializable {

    @XmlElement(name = "first_name")
    @Size(min = 2, max = 30)
    private String firstName;

    @XmlElement(name = "last_name")
    @Size(min = 2, max = 30)
    private String lastName;

    @XmlElement(name = "salary")
    @Positive
    private double salary;

    @XmlElement(name = "age")
    @Min(18)
    @Max(80)
    private int age;

    @XmlElement(name = "exploring_from")
    @XmlJavaTypeAdapter(LocalDateTimeAdapterXML.class)
    private LocalDate exploringFrom;

    @XmlElement(name = "exploring_volcano_id")
    private Long exploringVolcanoId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getExploringFrom() {
        return exploringFrom;
    }

    public void setExploringFrom(LocalDate exploringFrom) {
        this.exploringFrom = exploringFrom;
    }

    public Long getExploringVolcanoId() {
        return exploringVolcanoId;
    }

    public void setExploringVolcanoId(Long exploringVolcanoId) {
        this.exploringVolcanoId = exploringVolcanoId;
    }
}
