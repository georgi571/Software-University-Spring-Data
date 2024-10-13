package _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.imports;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarSeedDTO {

    @XmlElement(name = "make")
    private String make;

    @XmlElement(name = "model")
    private String model;

    @XmlElement(name = "travelled-distance")
    private long travelledDistance;

    public CarSeedDTO() {
    }

    public CarSeedDTO(String make, String model, long travelledDistance) {
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

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
