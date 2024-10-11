package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CarAndParts;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class CarDTO implements Serializable {

    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private BigDecimal travelledDistance;

    public CarDTO() {
    }

    public CarDTO(String make, String model, BigDecimal travelledDistance) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
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

    public BigDecimal getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(BigDecimal travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
