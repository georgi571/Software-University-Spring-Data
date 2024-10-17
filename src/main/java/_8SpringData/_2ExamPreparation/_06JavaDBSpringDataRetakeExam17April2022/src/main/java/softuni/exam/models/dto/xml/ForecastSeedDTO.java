package softuni.exam.models.dto.xml;

import softuni.exam.util.adapters.LocalDateTimeAdapterXML;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalTime;

@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastSeedDTO implements Serializable {

    @XmlElement(name = "day_of_week")
    @NotNull
    private String dayOfWeek;

    @XmlElement(name = "max_temperature")
    @DecimalMin(value = "-20")
    @DecimalMax(value = "60")
    @NotNull
    private double maxTemperature;

    @XmlElement(name = "min_temperature")
    @DecimalMin(value = "-50")
    @DecimalMax(value = "40")
    @NotNull
    private double minTemperature;

    @XmlElement(name = "sunrise")
    @XmlJavaTypeAdapter(LocalDateTimeAdapterXML.class)
    @NotNull
    private LocalTime sunrise;

    @XmlElement(name = "sunset")
    @XmlJavaTypeAdapter(LocalDateTimeAdapterXML.class)
    @NotNull
    private LocalTime sunset;

    @XmlElement(name = "city")
    private Long city;

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }
}
