package softuni.exam.models.entity;

import softuni.exam.models.enums.VolcanoType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "volcanoes")
public class Volcano extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "elevation", nullable = false)
    private int elevation;

    @Enumerated(EnumType.STRING)
    @Column(name = "volcano_type")
    private VolcanoType volcanoType;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "last_eruption")
    private LocalDate lastEruption;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @OneToMany(mappedBy = "volcano")
    private Set<Volcanologist> volcanologists;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public LocalDate getLastEruption() {
        return lastEruption;
    }

    public void setLastEruption(LocalDate lastEruption) {
        this.lastEruption = lastEruption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VolcanoType getVolcanoType() {
        return volcanoType;
    }

    public void setVolcanoType(VolcanoType volcanoType) {
        this.volcanoType = volcanoType;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Volcanologist> getVolcanologists() {
        return volcanologists;
    }

    public void setVolcanologists(Set<Volcanologist> volcanologists) {
        this.volcanologists = volcanologists;
    }
}
