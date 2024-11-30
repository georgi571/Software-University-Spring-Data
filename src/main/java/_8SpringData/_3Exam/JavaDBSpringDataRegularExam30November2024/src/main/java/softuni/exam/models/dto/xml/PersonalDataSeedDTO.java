package softuni.exam.models.dto.xml;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import softuni.exam.util.adapters.LocalDateAdapterXML;

import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement(name = "personal_data")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonalDataSeedDTO implements Serializable {

    @XmlElement(name = "age")
    @Min(1)
    @Max(100)
    private int age;

    @XmlElement(name = "gender")
    private char gender;

    @XmlElement(name = "birth_date")
    @XmlJavaTypeAdapter(LocalDateAdapterXML.class)
    private LocalDate birthDate;

    @XmlElement(name = "card_number")
    @Size(min = 9, max = 9)
    private String cardNumber;

    @Min(1)
    @Max(100)
    public int getAge() {
        return age;
    }

    public void setAge(@Min(1) @Max(100) int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public @Size(min = 9, max = 9) String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(@Size(min = 9, max = 9) String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
