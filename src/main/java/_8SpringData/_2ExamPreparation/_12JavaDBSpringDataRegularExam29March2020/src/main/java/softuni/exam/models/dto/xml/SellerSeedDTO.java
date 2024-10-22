package softuni.exam.models.dto.xml;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "seller")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerSeedDTO implements Serializable {

    @XmlElement(name = "first-name")
    @Size(min = 2, max = 20)
    private String firstName;

    @XmlElement(name = "last-name")
    @Size(min = 2, max = 20)
    private String lastName;

    @XmlElement(name = "email")
    @Email
    private String email;

    @XmlElement(name = "rating")
    @NotNull
    private String rating;

    @XmlElement(name = "town")
    @NotNull
    private String town;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
