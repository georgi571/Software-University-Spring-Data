package softuni.exam.models.enity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "kilometers", nullable = false)
    private int kilometers;

    @Column(name = "registered_on", nullable = false)
    private LocalDate registeredOn;

    @OneToMany(mappedBy = "car")
    private Set<Offer> offerSet;
    @OneToMany(mappedBy = "car")
    private Set<Picture> pictures = new HashSet<>();

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Set<Offer> getOfferSet() {
        return offerSet;
    }

    public void setOfferSet(Set<Offer> offerSet) {
        this.offerSet = offerSet;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }
}
