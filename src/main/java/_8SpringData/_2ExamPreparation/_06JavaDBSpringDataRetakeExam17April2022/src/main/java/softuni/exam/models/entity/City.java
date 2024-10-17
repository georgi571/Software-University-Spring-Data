package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cities")
public class City extends BaseEntity {

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "population", nullable = false)
    private int population;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @OneToMany(mappedBy = "city")
    private Set<Forecast> forecastSet = new HashSet<>();

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Forecast> getForecastSet() {
        return forecastSet;
    }

    public void setForecastSet(Set<Forecast> forecastSet) {
        this.forecastSet = forecastSet;
    }
}
