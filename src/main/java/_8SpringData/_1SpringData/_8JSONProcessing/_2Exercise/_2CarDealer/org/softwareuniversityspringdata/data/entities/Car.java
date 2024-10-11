package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "travelled_distance")
    private long travelledDistance;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cars_parts",
    joinColumns = @JoinColumn(name = "cars_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "parts_id", referencedColumnName = "id"))
    private Set<Part> parts;

    @OneToOne(mappedBy = "car")
    private Sale sale;

    public Car() {
        this.parts = new HashSet<>();
    }

    public Car(String make, String model, long travelledDistance) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
        this.parts = new HashSet<>();
    }

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

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
