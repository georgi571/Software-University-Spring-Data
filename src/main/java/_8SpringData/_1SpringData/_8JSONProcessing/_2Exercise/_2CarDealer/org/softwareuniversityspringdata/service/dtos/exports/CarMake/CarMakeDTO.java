package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CarMake;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class CarMakeDTO implements Serializable {

    @Expose
    private long id;

    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private long travelledDistance;

    public CarMakeDTO() {
    }

    public CarMakeDTO(long id, String make, String model, long travelledDistance) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
