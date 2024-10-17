package softuni.exam.models.dto.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PersonSeedDTO implements Serializable {

    @Expose
    @Email
    private String email;

    @Expose
    @Size(min = 2, max = 30)
    private String firstName;

    @Expose
    @Size(min = 2, max = 30)
    private String lastName;

    @Expose
    @SerializedName("phone")
    @Size(min = 2, max = 13)
    private String phoneNumber;

    @Expose
    private String statusType;

    @Expose
    private Long country;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLasName() {
        return lastName;
    }

    public void setLasName(String lasName) {
        this.lastName = lasName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }
}
