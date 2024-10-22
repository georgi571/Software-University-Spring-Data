package softuni.exam.models.enity;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "date_and_time", nullable = false)
    private LocalDateTime dateAndTime;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @ManyToMany(mappedBy = "pictureSet")
    private Set<Offer> offerSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Set<Offer> getOfferSet() {
        return offerSet;
    }

    public void setOfferSet(Set<Offer> offerSet) {
        this.offerSet = offerSet;
    }
}
